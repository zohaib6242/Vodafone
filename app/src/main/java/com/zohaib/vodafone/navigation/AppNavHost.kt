package com.zohaib.vodafone.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zohaib.vodafone.presentation.mvi.LoginEffect
import com.zohaib.vodafone.presentation.mvi.LoginIntent
import com.zohaib.vodafone.presentation.mvi.LoginViewModel
import com.zohaib.vodafone.presentation.screens.HomeScreen
import com.zohaib.vodafone.presentation.screens.LoginScreen

object Routes {
    const val LOGIN = "login"
    const val HOME = "home"
}

@Composable
fun AppNavHost(navController: NavHostController) {
    val loginViewModel: LoginViewModel = hiltViewModel()
    val loginState = loginViewModel.state.collectAsState()
    val effect = loginViewModel.effect.collectAsState(initial = null)

    LaunchedEffect(effect.value) {
        when (effect.value) {
            LoginEffect.NavigateToHome -> {
                navController.navigate(Routes.HOME) {
                    popUpTo(Routes.LOGIN) { inclusive = true }
                }
            }
            LoginEffect.NavigateToLogin -> {
                navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.HOME) { inclusive = true }
                }
            }
            else -> Unit
        }
    }

    NavHost(navController = navController, startDestination = Routes.LOGIN) {
        composable(Routes.LOGIN) {
            LoginScreen(viewModel = loginViewModel)
        }

        composable(Routes.HOME) {
            HomeScreen(
                user = loginState.value.user,
                onLogout = { loginViewModel.process(LoginIntent.Logout) }
            )
        }
    }
}

