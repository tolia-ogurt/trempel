package com.example.login.di

import com.example.login.service.SignInService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
internal class LoginNetworkModule {

    @Provides
    fun provideSignInService(retrofit: Retrofit): SignInService {
        return retrofit.create(SignInService::class.java)
    }
}