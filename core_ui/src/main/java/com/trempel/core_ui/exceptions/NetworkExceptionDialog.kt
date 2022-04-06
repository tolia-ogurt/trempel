package com.trempel.core_ui.exceptions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.core_ui.R
import com.example.core_ui.databinding.NetworkExceptionDialogBinding

class NetworkExceptionDialog : DialogFragment() {

    var retryCall: () -> Unit = { }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = NetworkExceptionDialogBinding.inflate(layoutInflater)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.rounded_corner_exception_dialog)
        binding.btnCloseDialog.setOnClickListener {
            dismiss()
        }
        binding.btnRetry.setOnClickListener {
            retryCall()
            dismiss()
        }
        return binding.root
    }

    companion object {
        const val NETWORK_EXCEPTION_DIALOG = "networkExceptionDialog"
    }
}