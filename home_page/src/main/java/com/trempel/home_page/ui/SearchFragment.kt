package com.trempel.home_page.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.home_page.databinding.SearchResultFragmentBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class SearchFragment : Fragment() {

    @Inject
    lateinit var viewModel: SearchViewModel
    val args: SearchFragmentArgs by navArgs()

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
        }.root
    }
}