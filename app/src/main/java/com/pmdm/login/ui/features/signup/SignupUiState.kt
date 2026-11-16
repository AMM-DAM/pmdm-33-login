package com.pmdm.login.ui.features.signup

data class SignupUiState(
    val email: String = "",
    val username: String = "",
    val password: String = "",
    val passwordRepeat: String = "",
    val isRepeatPasswordMatch: String = "",
    val acceptToC: Boolean = false,
    val validacion: ValidacionSignupUiState = ValidacionSignupUiState()
)
