package com.example.login.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.login.databinding.SignInFragmentBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class SignInFragment : Fragment() {

    @Inject
    lateinit var viewModel: SignInViewModel

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