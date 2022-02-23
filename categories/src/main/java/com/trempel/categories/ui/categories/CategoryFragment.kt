package com.trempel.categories.ui.categories

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.categories.R
import com.example.categories.databinding.CategoryFragmentBinding
import com.trempel.core_ui.BaseFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class CategoryFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: CategoryViewModel
    override val isToolbarVisible: Boolean = true
    override val title: String by lazy { getText(R.string.all_category_title).toString() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return CategoryFragmentBinding.inflate(layoutInflater).apply {
            this.lifecycleOwner = this@CategoryFragment
            this.viewModel = this@CategoryFragment.viewModel
        }.also {
            observeExceptionResponse()
            viewModel.loadProduct()
        }.root
    }

    private fun observeExceptionResponse() {
        viewModel.errorLiveData.observe(this.viewLifecycleOwner, {
            Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
        })
    }
}