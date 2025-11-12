package com.zohaib.vodafone.di

import com.zohaib.vodafone.data.repository.AuthRepositoryImpl
import com.zohaib.vodafone.domain.repository.AuthRepository
import com.zohaib.vodafone.domain.usecase.GetCurrentUserUseCase
import com.zohaib.vodafone.domain.usecase.LoginUseCase
import com.zohaib.vodafone.domain.usecase.LogoutUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideLogoutUseCase(repo: AuthRepository): LogoutUseCase = LogoutUseCase(repo)

    @Provides
    fun provideLoginUseCase(repo: AuthRepository): LoginUseCase = LoginUseCase(repo)

    @Provides
    fun provideGetCurrentUserUseCase(repo: AuthRepository): GetCurrentUserUseCase = GetCurrentUserUseCase(repo)
}