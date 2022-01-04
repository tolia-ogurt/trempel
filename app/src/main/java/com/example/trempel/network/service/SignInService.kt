package com.example.trempel.network.service

import com.example.trempel.network.model.LoginRequest
import com.example.trempel.network.model.LoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SignInService {

    @POST("/auth/login")
    fun signInApp(@Body request: LoginRequest): Single<LoginResponse>
}