package com.trempel.login.ui

import androidx.databinding.ktx.BuildConfig
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trempel.core_ui.SingleLiveEvent
import com.trempel.core_ui.exceptions.TrempelException
import com.trempel.login.repo.SignInRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val signInRepository: SignInRepository
) : ViewModel() {

    private val _successLiveData = MutableLiveData<Unit>()
    val successLiveData: LiveData<Unit> get() = _successLiveData
    private val _errorLiveData = SingleLiveEvent<TrempelException?>()
    val errorLiveData: LiveData<TrempelException?> get() = _errorLiveData
    private var disposable: Disposable? = null
    val loginEditTextData = MutableLiveData<String>()
    val passwordEditTextData = MutableLiveData<String>()

    fun login() {
        val email = loginEditTextData.value.toString()
        val password = passwordEditTextData.value.toString()
        disposable = signInRepository.signInApp(email, password)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _successLiveData.value = Unit
            }, { error ->
                _errorLiveData.value = error as? TrempelException
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