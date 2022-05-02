package com.trempel.profile.service

import androidx.lifecycle.MutableLiveData
import com.trempel.profile.model.ProfileModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileService {

    @GET("/users/1")
    suspend fun getUser(): ProfileModel
}