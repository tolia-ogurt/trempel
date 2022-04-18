package com.trempel.bag.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trempel.bag.databinding.BagFragmentBinding
import com.trempel.core_ui.BaseFragment
import com.trempel.core_ui.exceptions.ExceptionDialog
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class BagFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: BagViewModel
    override val isToolbarVisible: Boolean = false

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
            this.viewModel = this@BagFragment.viewModel
            this.lifecycleOwner = this@BagFragment
        }.also {
            this.viewLifecycleOwner.lifecycle.addObserver(viewModel)
            observeExceptionResponse()
        }.root
    }

    private fun observeExceptionResponse() {
        viewModel.errorLiveData.observe(this.viewLifecycleOwner, {
            ExceptionDialog(it)
                .apply { retryCall = viewModel::loadData }
                .show(childFragmentManager, ExceptionDialog.SERVICE_EXCEPTION_DIALOG)
        })
    }
}