package com.trempel.login.repo

import com.trempel.login.model.LoginRequest
import com.trempel.login.service.SignInService
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

internal class SignInRepositoryImpl @Inject constructor(
    private val sessionManager: SessionManager,
    private val retrofitService: SignInService
) : SignInRepository {

    override fun signInApp(email: String, password: String): Completable {
        return retrofitService.signInApp(LoginRequest(email, password))
            .doOnSuccess { sessionManager.authToken = (it.token) }
            .subscribeOn(Schedulers.io()).ignoreElement()
    }
}
