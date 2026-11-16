package com.pmdm.login.ui.features.signup

sealed interface SignupEvent {
    data class OnEmailChanged(val email: String) : SignupEvent
    data class OnUsernameChanged(val username: String) : SignupEvent
    data class OnPasswordChanged(val password: String) : SignupEvent
    data class OnPasswordRepeatChanged(val passwordRepeat: String) : SignupEvent
    data object OnAcceptToCChanged : SignupEvent
    data object OnSignup : SignupEvent
    data object OnNavigateToLogin : SignupEvent
}