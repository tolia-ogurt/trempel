package com.example.trempel

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.trempel.databinding.PdpFragmentBinding
import javax.inject.Inject

internal class PdpFragment : Fragment() {

    @Inject
    lateinit var viewModel: PdpViewModel

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
            viewModel.loadProduct()
        }.root
    }
}