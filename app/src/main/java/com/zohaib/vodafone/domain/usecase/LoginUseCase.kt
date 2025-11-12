package com.zohaib.vodafone.domain.usecase

import com.zohaib.vodafone.domain.model.User
import com.zohaib.vodafone.domain.repository.AuthRepository

class LoginUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String): Result<User> {
        return repository.login(username, password)
    }
}