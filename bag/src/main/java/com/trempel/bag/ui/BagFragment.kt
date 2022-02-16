package com.trempel.bag.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.trempel.bag.databinding.BagFragmentBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class BagFragment : Fragment() {

    @Inject
    lateinit var viewModel: BagViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return BagFragmentBinding.inflate(layoutInflater).apply {
            this.lifecycleOwner = this@BagFragment
            this.viewModel = this@BagFragment.viewModel
        }.also {
            viewModel.loadData()
        }.root
    }


}