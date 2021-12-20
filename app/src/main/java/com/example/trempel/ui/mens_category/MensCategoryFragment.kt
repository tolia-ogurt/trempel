package com.example.trempel.ui.mens_category

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trempel.MyApplication
import com.example.trempel.databinding.MensCategoryFragmentBinding
import javax.inject.Inject

internal class MensCategoryFragment : Fragment() {

    private var _binding: MensCategoryFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = MensCategoryRecyclerAdapter().apply {
        this.onItemClicked = this@MensCategoryFragment::onItemClicked
    }

    @Inject
    lateinit var viewModel: MensCategoryViewModel
    val args: MensCategoryFragmentArgs by navArgs()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MensCategoryFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        initializationRecyclerAdapter()
        observeLoadProduct()
        observeExceptionResponse()
        return binding.root
    }

    private fun onItemClicked(id: Int) {
        val action = MensCategoryFragmentDirections.actionMensCategoryFragmentToPdpFragment(id)
        findNavController().navigate(action)
    }

    private fun initializationRecyclerAdapter() {
        val recyclerView = binding.recyclerViewMensClothing
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerViewMensClothing.adapter = adapter
    }

    private fun observeLoadProduct() {
        viewModel.items.observe(this.viewLifecycleOwner, {
            adapter.itemList = it
            adapter.notifyDataSetChanged()
        })
        binding.tvNameCategory.text = args.nameCategory.title
        viewModel.loadProduct(args.nameCategory)
    }

    private fun observeExceptionResponse() {
        viewModel.errorLiveData.observe(this.viewLifecycleOwner, {
            Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
        })
    }
}