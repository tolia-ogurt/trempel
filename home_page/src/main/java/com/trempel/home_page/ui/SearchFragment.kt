package com.trempel.home_page.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.home_page.databinding.SearchResultFragmentBinding
import com.trempel.core_ui.BaseFragment
import com.trempel.core_ui.exceptions.ExceptionDialog
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class SearchFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: SearchViewModel
    private val args: SearchFragmentArgs by navArgs()
    override val isToolbarVisible: Boolean = true
    override val title: String by lazy { args.keyWord.replaceFirstChar { it.uppercase() } }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return SearchResultFragmentBinding.inflate(layoutInflater).apply {
            this.lifecycleOwner = this@SearchFragment
            this.viewModel = this@SearchFragment.viewModel
        }.also {
            viewModel.loadSearchResult(args.keyWord)
            observeExceptionResponse()
        }.root
    }

    private fun observeExceptionResponse() {
        viewModel.errorLiveData.observe(
            this.viewLifecycleOwner,
            {
                ExceptionDialog(it)
                    .apply { retryCall = { viewModel.loadSearchResult(args.keyWord) } }
                    .show(childFragmentManager, ExceptionDialog.SERVICE_EXCEPTION_DIALOG)
            }
        )
    }
}
