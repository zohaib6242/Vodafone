package com.zohaib.vodafone.data.repository

import com.zohaib.vodafone.data.api.AuthApi
import com.zohaib.vodafone.data.model.LoginRequest
import com.zohaib.vodafone.domain.model.User
import com.zohaib.vodafone.domain.repository.AuthRepository
import com.zohaib.vodafone.domain.repository.SecureStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val storage: SecureStorage
) : AuthRepository {

    override suspend fun login(username: String, password: String): Result<User> = withContext(Dispatchers.IO) {
        try {
            val req = LoginRequest(username = username, password = password)
            val res = api.login(req)
            val user = User(
                id = res.id,
                username = res.username,
                email = res.email,
                firstName = res.firstName,
                lastName = res.lastName,
                image = res.image
            )
            storage.saveUser(user)
            res.accessToken?.let { storage.saveToken(it) }
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCurrentUser(token: String): Result<User> = withContext(Dispatchers.IO) {
        try {
            val bearer = "Bearer $token"
            val res = api.me(bearer)
            val user = User(
                id = res.id,
                username = res.username,
                email = res.email,
                firstName = res.firstName,
                lastName = res.lastName,
                image = res.image
            )
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun saveAuthToken(token: String) {
        storage.saveToken(token)
    }

    override suspend fun getAuthToken(): String? = storage.getToken()

    override suspend fun clearAuth() {
        storage.clearAll()
    }
}