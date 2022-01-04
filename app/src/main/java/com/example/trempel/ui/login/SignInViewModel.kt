package com.example.trempel.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trempel.BuildConfig
import com.example.trempel.SignInRepository
import io.reactivex.disposables.Disposable
import javax.inject.Inject

internal class SignInViewModel @Inject constructor(
    private val signInRepository: SignInRepository
) : ViewModel() {

    private val _successLiveData = MutableLiveData<Unit>()
    val successLiveData: LiveData<Unit> get() = _successLiveData
    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData
    private var disposable: Disposable? = null
    val loginEditTextData = MutableLiveData<String>()
    val passwordEditTextData = MutableLiveData<String>()

    fun login() {
        val email = loginEditTextData.value.toString()
        val password = passwordEditTextData.value.toString()
        disposable = signInRepository.signInAp(email, password)
            .subscribe({
                _successLiveData.value = Unit
            }, { error ->
                _errorLiveData.value = error.message
            })
    }

    fun setLoginDataForDebug() {
        if (BuildConfig.DEBUG) {
            loginEditTextData.value = "mor_2314"
            passwordEditTextData.value = "83r5^_"
        }
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}