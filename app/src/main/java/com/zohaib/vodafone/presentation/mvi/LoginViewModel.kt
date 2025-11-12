package com.zohaib.vodafone.presentation.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zohaib.vodafone.domain.usecase.GetCurrentUserUseCase
import com.zohaib.vodafone.domain.usecase.LoginUseCase
import com.zohaib.vodafone.domain.usecase.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    private val _effect = MutableSharedFlow<LoginEffect>(extraBufferCapacity = 1)
    val effect = _effect.asSharedFlow()

    init {
        process(LoginIntent.CheckSession)
    }

    fun process(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.InputUsername -> _state.value = _state.value.copy(username = intent.username)
            is LoginIntent.InputPassword -> _state.value = _state.value.copy(password = intent.password)
            LoginIntent.TogglePasswordVisibility -> _state.value = _state.value.copy(passwordVisible = !_state.value.passwordVisible)
            LoginIntent.Submit -> submit()
            LoginIntent.CheckSession -> checkSession()
            LoginIntent.Logout -> logout()
        }
    }

    private fun submit() {
        val username = _state.value.username.trim()
        val password = _state.value.password
        if (username.isEmpty() || password.isEmpty()) {
            viewModelScope.launch { _effect.emit(LoginEffect.ShowError("Please fill all fields")) }
            return
        }

        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)

            val result = loginUseCase(username, password)
            result
                .onSuccess { user ->
                    _state.value = _state.value.copy(isLoading = false, user = user)
                    _effect.emit(LoginEffect.NavigateToHome)
                }
                .onFailure { exception ->
                    _state.value = _state.value.copy(isLoading = false)
                    _effect.emit(LoginEffect.ShowError(exception.localizedMessage ?: "Login failed"))
                }
        }
    }

    private fun checkSession() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            val result = getCurrentUserUseCase()
            result
                .onSuccess { user ->
                    _state.value = _state.value.copy(isLoading = false, user = user)
                    _effect.emit(LoginEffect.NavigateToHome)
                }
                .onFailure {
                    _state.value = _state.value.copy(isLoading = false)
                }
        }
    }

    private fun logout() {
        viewModelScope.launch {
            logoutUseCase()
            _state.value = LoginState()
            _effect.emit(LoginEffect.NavigateToLogin)
        }
    }
}
