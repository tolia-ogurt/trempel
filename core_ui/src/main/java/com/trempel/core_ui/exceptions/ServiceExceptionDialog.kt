package com.trempel.core_ui.exceptions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.LifecycleOwner
import com.example.core_ui.R
import com.example.core_ui.databinding.ServiceExceptionDialogBinding

class ServiceExceptionDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ServiceExceptionDialogBinding.inflate(layoutInflater)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.rounded_corner_exception_dialog)
        binding.btnCloseDialog.setOnClickListener {
            dismiss()
        }
        return binding.root
    }

    companion object{
        const val SERVICE_EXCEPTION_DIALOG = "serviceExceptionDialog"
    }
}