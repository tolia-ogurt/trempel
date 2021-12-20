package com.example.trempel.ui.categories

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trempel.MyApplication
import com.example.trempel.R
import com.example.trempel.databinding.CategoryFragmentBinding
import com.example.trempel.network.CategoryDomainModel
import javax.inject.Inject

internal class CategoryFragment : Fragment(R.layout.category_fragment) {

    private var _binding: CategoryFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = CategoriesAdapter().apply {
        this.onItemClicked = this@CategoryFragment::onItemClicked
    }

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
        _binding = CategoryFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        initializationRecyclerAdapter()
        observeLoadProduct()
        observeExceptionResponse()
        return binding.root
    }

    private fun onItemClicked(category: CategoryDomainModel) {
        val action = CategoryFragmentDirections.actionCategoryFragmentToMensCategoryFragment(category)
        findNavController().navigate(action)
    }

    private fun initializationRecyclerAdapter() {
        val recyclerView = binding.recyclerViewAllCategories
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerViewAllCategories.adapter = adapter
    }

    private fun observeLoadProduct() {
        viewModel.categories.observe(this.viewLifecycleOwner, {
            adapter.categoryList = it
            adapter.notifyDataSetChanged()
        })
        viewModel.loadProduct()
    }

    private fun observeExceptionResponse() {
        viewModel.errorLiveData.observe(this.viewLifecycleOwner, {
            Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
        })
    }
}