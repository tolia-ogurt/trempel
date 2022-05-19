package com.trempel.profile.di

import com.trempel.profile.repo.ProfileRepository
import com.trempel.profile.repo.ProfileRepositoryImpl
import dagger.Binds
import dagger.Module

@Module(includes = [ProfileNetworkModule::class])
abstract class ProfileDataModule {

    @Binds
    abstract fun provideProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl): ProfileRepository
}
