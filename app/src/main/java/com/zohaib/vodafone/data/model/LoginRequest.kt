package com.zohaib.vodafone.data.model

data class LoginRequest(
    val username: String,
    val password: String,
    val expiresInMins: Int = 30
)