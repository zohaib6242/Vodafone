package com.zohaib.vodafone.domain.usecase

import com.zohaib.vodafone.domain.model.User
import com.zohaib.vodafone.domain.repository.AuthRepository

class GetCurrentUserUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(): Result<User> {
        val token = repository.getAuthToken() ?: return Result.failure(Exception("No token"))
        return repository.getCurrentUser(token)
    }
}