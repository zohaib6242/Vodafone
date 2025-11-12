package com.zohaib.vodafone.domain.repository

import com.zohaib.vodafone.domain.model.User

interface SecureStorage {
    fun saveUser(user: User)
    fun saveToken(token: String)
    fun getToken(): String?
    fun clearAll()
}