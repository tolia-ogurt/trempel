package com.example.trempel.ui.pdp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trempel.MyApplication
import com.example.trempel.databinding.PdpFragmentBinding
import javax.inject.Inject

internal class PdpFragment : Fragment() {

    @Inject
    lateinit var viewModel: PdpViewModel
    private val args: PdpFragmentArgs by navArgs()
    private val adapter = RecentlyViewedAdapter().apply {
        onItemClicked = this@PdpFragment::onItemClicked
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return PdpFragmentBinding.inflate(layoutInflater).apply {
            this.lifecycleOwner = this@PdpFragment
            this.viewModel = this@PdpFragment.viewModel
        }.also {
            it.initializationRecyclerAdapter()
            viewModel.loadProduct(args.productId)
            observeExceptionResponse()
            viewModel.getRecentlyViewedProduct(args.productId)
            observeRecentlyViewedProduct()
        }.root
    }

    private fun onItemClicked(id: Int) {
        val action = PdpFragmentDirections.actionPdpFragmentSelf(id)
        findNavController().navigate(action)
    }

    private fun observeExceptionResponse() {
        viewModel.errorLiveData.observe(this.viewLifecycleOwner, {
            Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun PdpFragmentBinding.initializationRecyclerAdapter() {
        rvRecentlyView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvRecentlyView.adapter = adapter
    }

    private fun observeRecentlyViewedProduct() {
        viewModel.allRecentlyViewedProduct.observe(this.viewLifecycleOwner, {
            adapter.itemList = it
            adapter.notifyDataSetChanged()
        })
    }
}