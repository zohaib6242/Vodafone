package com.zohaib.vodafone.di

import android.content.Context
import com.zohaib.vodafone.data.storage.AppPreferencesImpl
import com.zohaib.vodafone.data.storage.SecureStorageImpl
import com.zohaib.vodafone.domain.repository.SecureStorage
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @Singleton
    fun provideSecureStorage(@ApplicationContext context: Context): SecureStorage =
        SecureStorageImpl(context)
}
