package com.example.login.service

import com.example.login.model.LoginRequest
import com.example.login.model.LoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInService {

    @POST("/auth/login")
    fun signInApp(@Body request: LoginRequest): Single<LoginResponse>
}