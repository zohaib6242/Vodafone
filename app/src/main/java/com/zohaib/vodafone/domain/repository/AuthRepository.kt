package com.zohaib.vodafone.domain.repository

import com.zohaib.vodafone.domain.model.User

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<User>
    suspend fun getCurrentUser(token: String): Result<User>
    suspend fun saveAuthToken(token: String)
    suspend fun getAuthToken(): String?
    suspend fun clearAuth()
}