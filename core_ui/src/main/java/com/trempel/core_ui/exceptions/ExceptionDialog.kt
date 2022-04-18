package com.trempel.core_ui.exceptions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.core_ui.R
import com.example.core_ui.databinding.NetworkExceptionDialogBinding
import com.example.core_ui.databinding.ServiceExceptionDialogBinding

class ExceptionDialog(private val trempelException: TrempelException?) : DialogFragment() {

    var retryCall: () -> Unit = { }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return if (trempelException is TrempelException.Network) {
            NetworkExceptionDialogBinding.inflate(layoutInflater)
            val bindingNetworkEx = NetworkExceptionDialogBinding.inflate(layoutInflater)
            dialog?.window?.setBackgroundDrawableResource(R.drawable.rounded_corner_exception_dialog)
            bindingNetworkEx.btnCloseDialog.setOnClickListener {
                dismiss()
            }
            bindingNetworkEx.btnRetry.setOnClickListener {
                retryCall()
                dismiss()
            }
            bindingNetworkEx.root
        } else {
            dialog?.window?.setBackgroundDrawableResource(R.drawable.rounded_corner_exception_dialog)
            val bindingServiceEx = ServiceExceptionDialogBinding.inflate(layoutInflater)
            bindingServiceEx.btnCloseDialog.setOnClickListener {
                dismiss()
            }
            bindingServiceEx.root
        }
    }

    companion object {
        const val SERVICE_EXCEPTION_DIALOG = "exceptionDialog"
    }
}