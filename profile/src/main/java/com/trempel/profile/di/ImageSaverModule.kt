package com.trempel.profile.di

import android.content.Context
import com.trempel.core_ui.ImageSaver
import dagger.Module
import dagger.Provides

@Module
class ImageSaverModule {

    @Provides
    fun provideImageSaver(context: Context): ImageSaver {
        return ImageSaver(context)
    }
}
