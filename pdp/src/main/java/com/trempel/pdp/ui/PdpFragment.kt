package com.trempel.pdp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.pdp.databinding.PdpFragmentBinding
import com.trempel.core_ui.BaseFragment
import com.trempel.core_ui.exceptions.NetworkExceptionDialog
import com.trempel.core_ui.exceptions.NetworkExceptionDialog.Companion.NETWORK_EXCEPTION_DIALOG
import com.trempel.core_ui.exceptions.ServiceExceptionDialog
import com.trempel.core_ui.exceptions.ServiceExceptionDialog.Companion.SERVICE_EXCEPTION_DIALOG
import com.trempel.core_ui.exceptions.TrempelException
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PdpFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: PdpViewModel
    private val args: PdpFragmentArgs by navArgs()
    override val isToolbarVisible: Boolean = false
    override val isBottomNavVisible: Boolean = false

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
            if (it is TrempelException.Network) {
                NetworkExceptionDialog()
                    .apply { retryCall = { viewModel.loadProduct(args.productId) } }
                    .show(childFragmentManager, NETWORK_EXCEPTION_DIALOG)
            } else {
                ServiceExceptionDialog().show(childFragmentManager, SERVICE_EXCEPTION_DIALOG)
            }
        })
    }
}