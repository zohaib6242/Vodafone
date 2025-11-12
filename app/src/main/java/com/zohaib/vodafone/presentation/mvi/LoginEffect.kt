package com.zohaib.vodafone.presentation.mvi

sealed class LoginEffect {
    data class ShowError(val message: String) : LoginEffect()
    object NavigateToHome : LoginEffect()
    object NavigateToLogin : LoginEffect()
}