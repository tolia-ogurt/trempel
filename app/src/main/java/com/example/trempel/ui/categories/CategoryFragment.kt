package com.example.trempel.ui.categories

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trempel.MyApplication
import com.example.trempel.R
import com.example.trempel.databinding.CategoryFragmentBinding
import com.example.trempel.databinding.CategoryProductsFragmentBinding
import javax.inject.Inject

internal class CategoryFragment : Fragment(R.layout.category_fragment) {

    @Inject
    lateinit var viewModel: CategoryViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
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
            it.navigateWithToolbar()
            observeExceptionResponse()
            viewModel.loadProduct()
        }.root
    }

    private fun observeExceptionResponse() {
        viewModel.errorLiveData.observe(this.viewLifecycleOwner, {
            Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun CategoryFragmentBinding.navigateWithToolbar() {
        this.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}