package com.example.pdp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.pdp.databinding.PdpFragmentBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PdpFragment : Fragment() {

    @Inject
    lateinit var viewModel: PdpViewModel
    private val args: PdpFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
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
            viewModel.loadProduct(args.productId)
            observeExceptionResponse()
            viewModel.getRecentlyViewedProduct(args.productId)
        }.root
    }

    private fun observeExceptionResponse() {
        viewModel.errorLiveData.observe(this.viewLifecycleOwner, {
            Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
        })
    }
}