package com.zohaib.vodafone.presentation.mvi

import com.zohaib.vodafone.domain.model.User

data class LoginState(
    val username: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val user: User? = null
)