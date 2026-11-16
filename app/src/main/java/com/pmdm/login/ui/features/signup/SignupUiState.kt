package com.pmdm.login.ui.features.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

data class SignupUiState(
    val email: String = "",
    val username: String = "",
    val password: String = "",
    val passwordRepeat: String = "",
    val isRepeatPasswordMatch: String = "",
    val acceptToC: Boolean = false
)
