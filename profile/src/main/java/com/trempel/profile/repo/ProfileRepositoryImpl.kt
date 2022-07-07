package com.trempel.profile.repo

import com.trempel.profile.model.ProfileModel
import com.trempel.profile.service.ProfileService
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileService: ProfileService
) : ProfileRepository {

    override suspend fun getUser(): ProfileModel {
        return profileService.getUser()
    }
}
