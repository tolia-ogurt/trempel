package com.trempel.login.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.login.databinding.SignInFragmentBinding
import com.trempel.core_ui.BaseFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class SignInFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: SignInViewModel
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
        return SignInFragmentBinding.inflate(layoutInflater).apply {
            this.lifecycleOwner = this@SignInFragment
            this.viewModel = this@SignInFragment.viewModel
        }.also {
            observeSuccessLogin()
            observeExceptionResponse()
        }.root
    }

    private fun observeSuccessLogin() {
        viewModel.successLiveData.observe(this.viewLifecycleOwner, {
            findNavController().popBackStack()
        })
    }

    private fun observeExceptionResponse() {
        viewModel.errorLiveData.observe(this.viewLifecycleOwner, {
            Toast.makeText(this.context, "Login or password is not correct", Toast.LENGTH_SHORT)
                .show()
        })
    }
}