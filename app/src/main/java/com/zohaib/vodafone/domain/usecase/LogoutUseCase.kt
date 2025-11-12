package com.zohaib.vodafone.domain.usecase

import com.zohaib.vodafone.domain.repository.AuthRepository

class LogoutUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke() {
        repository.clearAuth()
    }
}
