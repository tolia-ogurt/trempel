package com.trempel.profile.di

import com.trempel.profile.service.ProfileService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ProfileNetworkModule {

    @Provides
    fun provideProfileService(retrofit: Retrofit): ProfileService {
        return retrofit.create(ProfileService::class.java)
    }
}
