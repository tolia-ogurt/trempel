package com.example.trempel

import com.example.trempel.network.model.LoginRequest
import com.example.trempel.network.service.SignInService
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

internal class SignInRepository @Inject constructor(
    private val sessionManager: SessionManager, private val retrofitService: SignInService
) {

    fun signInAp(email: String, password: String): Completable {
        return retrofitService.signInApp(LoginRequest(email, password))
            .doOnSuccess { sessionManager.authToken = (it.token) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).ignoreElement()
    }
}