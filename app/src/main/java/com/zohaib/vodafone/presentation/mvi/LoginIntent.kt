package com.zohaib.vodafone.presentation.mvi

sealed class LoginIntent {
    data class InputUsername(val username: String) : LoginIntent()
    data class InputPassword(val password: String) : LoginIntent()
    object TogglePasswordVisibility : LoginIntent()
    object Submit : LoginIntent()
    object CheckSession : LoginIntent()
    object Logout : LoginIntent()
}