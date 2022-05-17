package com.trempel.profile.service

import com.trempel.profile.model.ProfileModel
import retrofit2.http.GET

interface ProfileService {

    @GET("/users/8")
    suspend fun getUser(): ProfileModel
}