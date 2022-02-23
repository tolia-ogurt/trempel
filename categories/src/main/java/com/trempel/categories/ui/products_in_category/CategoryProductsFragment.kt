package com.trempel.categories.ui.products_in_category

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.categories.databinding.CategoryProductsFragmentBinding
import com.trempel.core_ui.BaseFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class CategoryProductsFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: CategoryProductsViewModel
    private val args: CategoryProductsFragmentArgs by navArgs()
    override val isToolbarVisible: Boolean = true
    override val title: String by lazy { args.category.title.replaceFirstChar { it.uppercase() } }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return CategoryProductsFragmentBinding.inflate(layoutInflater).apply {
            this.lifecycleOwner = this@CategoryProductsFragment
            this.viewModel = this@CategoryProductsFragment.viewModel
        }.also {
            observeExceptionResponse()
            viewModel.loadProduct(args.category)
        }.root
    }

    private fun observeExceptionResponse() {
        viewModel.errorLiveData.observe(this.viewLifecycleOwner, {
            Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
        })
    }
}