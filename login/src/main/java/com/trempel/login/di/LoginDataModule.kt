package com.trempel.login.di

import com.trempel.login.repo.SignInRepository
import com.trempel.login.repo.SignInRepositoryImpl
import dagger.Binds
import dagger.Module

@Module(includes = [LoginNetworkModule::class])
internal abstract class LoginDataModule {

    @Binds
    abstract fun provideProductRepository(signInRepositoryImpl: SignInRepositoryImpl): SignInRepository
}
