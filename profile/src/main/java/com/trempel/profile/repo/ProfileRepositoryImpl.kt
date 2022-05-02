package com.trempel.profile.repo

import androidx.lifecycle.MutableLiveData
import com.trempel.profile.model.ProfileModel
import com.trempel.profile.service.ProfileService
import javax.inject.Inject
import javax.inject.Singleton

class ProfileRepositoryImpl @Inject constructor(
    private val profileService: ProfileService
) : ProfileRepository {

    override suspend fun getUser(): ProfileModel {
        return profileService.getUser()
    }
}