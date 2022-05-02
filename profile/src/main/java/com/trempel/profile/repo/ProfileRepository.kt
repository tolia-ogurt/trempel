package com.trempel.profile.repo

import com.trempel.profile.model.ProfileModel

interface ProfileRepository {

    suspend fun getUser(): ProfileModel
}