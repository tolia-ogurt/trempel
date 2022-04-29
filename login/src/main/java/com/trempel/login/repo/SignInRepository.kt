package com.trempel.login.repo

import io.reactivex.Completable

interface SignInRepository {

    fun signInApp(email: String, password: String): Completable
}
