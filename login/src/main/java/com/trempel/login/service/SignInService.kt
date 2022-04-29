package com.trempel.login.service

import com.trempel.login.model.LoginRequest
import com.trempel.login.model.LoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInService {

    @POST("/auth/login")
    fun signInApp(@Body request: LoginRequest): Single<LoginResponse>
}
