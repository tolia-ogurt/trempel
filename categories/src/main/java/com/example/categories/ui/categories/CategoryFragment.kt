package com.example.categories.ui.categories

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.categories.databinding.CategoryFragmentBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class CategoryFragment : Fragment() {

    @Inject
    lateinit var viewModel: CategoryViewModel

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