package com.pmdm.login.ui.features.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.pmdmiesbalmis.components.ui.composables.CheckboxWithLabel
import com.pmdm.login.ui.components.ForumTextField
import com.pmdm.login.ui.components.PasswordTextField
import com.pmdm.login.ui.theme.LoginTheme

@Composable
fun SignupForm(uiState: SignupUiState, onSignupEvent: (SignupEvent) -> Unit) {
    val modifierFillMaxWidth = Modifier.fillMaxWidth()
    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(),
    ) {
        Column(
            Modifier.padding(8.dp)
        ) {
            ForumTextField(
                value = uiState.email,
                onValueChanged = { onSignupEvent(SignupEvent.OnEmailChanged(it)) },
                label = "Correo electrónico",
                icon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "icono email"
                    )
                },
                errorMessage = uiState.validacion.validacionEmail.mensajeError,
                modifier = modifierFillMaxWidth
            )
            Spacer(Modifier.height(8.dp))
            ForumTextField(
                value = uiState.username,
                onValueChanged = { onSignupEvent(SignupEvent.OnUsernameChanged(it)) },
                label = "Nombre usuario",
                icon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "icono nombre usuario"
                    )
                },
                errorMessage = uiState.validacion.validacionUsername.mensajeError,
                modifier = modifierFillMaxWidth
            )
            Spacer(Modifier.height(8.dp))
            PasswordTextField(
                uiState.password,
                onPasswordValueChanged = { onSignupEvent(SignupEvent.OnPasswordChanged(it)) },
                errorMessage = uiState.validacion.validacionPassword.mensajeError,
                modifier = modifierFillMaxWidth
            )
            Spacer(Modifier.height(8.dp))
            PasswordTextField(
                uiState.passwordRepeat,
                onPasswordValueChanged = { onSignupEvent(SignupEvent.OnPasswordRepeatChanged(it)) },
                label = "Repetir contraseña",
                errorMessage = uiState.validacion.validacionPasswordRepeat.mensajeError,
                modifier = modifierFillMaxWidth
            )
            Spacer(Modifier.height(16.dp))
            CheckboxWithLabel(
                label = "Acepto los términos y condiciones",
                checkedState = uiState.acceptToC,
                onStateChange = { onSignupEvent(SignupEvent.OnAcceptToCChanged) },
            )
            Spacer(Modifier.height(16.dp))
            Button(onClick = { onSignupEvent(SignupEvent.OnSignup) }, modifierFillMaxWidth) {
                Text("Crear cuenta")
            }
        }
    }
}

@Composable
fun SignupScreen(uiState: SignupUiState, onSignupEvent: (SignupEvent) -> Unit) {
    Column(modifier = Modifier.padding(20.dp)) {
        SignupForm(uiState, onSignupEvent)
    }
}

@Preview(showBackground = true)
@Composable
fun SignupScreenPreview() {
    var signupUiState by remember { mutableStateOf(SignupUiState()) }
    val validadorSignup = remember { ValidadorSignup() }
    val onSignupEvent: (SignupEvent) -> Unit = { event ->
        when (event) {
            is SignupEvent.OnEmailChanged -> {
                val validacion = validadorSignup.validadorEmail.valida(event.email)
                signupUiState = signupUiState.copy(
                    email = event.email,
                    validacion = signupUiState.validacion.copy(validacionEmail = validacion)
                )
            }
            is SignupEvent.OnUsernameChanged -> {
                val validacion = validadorSignup.validadorUsername.valida(event.username)
                signupUiState = signupUiState.copy(
                    username = event.username,
                    validacion = signupUiState.validacion.copy(validacionUsername = validacion)
                )
            }
            is SignupEvent.OnPasswordChanged -> {
                val validacion = validadorSignup.validadorPassword.valida(event.password)
                signupUiState = signupUiState.copy(
                    password = event.password,
                    validacion = signupUiState.validacion.copy(validacionPassword = validacion)
                )
            }
            is SignupEvent.OnPasswordRepeatChanged -> {
                val validacion = validadorSignup.validadorPasswordRepeat.valida(
                    event.passwordRepeat,
                    signupUiState.password
                )
                signupUiState = signupUiState.copy(
                    passwordRepeat = event.passwordRepeat,
                    validacion = signupUiState.validacion.copy(validacionPasswordRepeat = validacion)
                )
            }
            SignupEvent.OnAcceptToCChanged -> {
                signupUiState = signupUiState.copy(
                    acceptToC = !signupUiState.acceptToC
                )
            }
            SignupEvent.OnSignup -> {
                val validacion = validadorSignup.valida(signupUiState)
                signupUiState = signupUiState.copy(validacion = validacion)
                if (!validacion.hayError) {
                    // Continuar...
                }
            }
        }
    }
    LoginTheme {
        SignupScreen(signupUiState, onSignupEvent)
    }
}


