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
import androidx.compose.material3.OutlinedTextField
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
            OutlinedTextField(
                value = uiState.email,
                onValueChange = { onSignupEvent(SignupEvent.OnEmailChanged(it)) },
                label = { Text("Correo electrónico") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "icono email"
                    )
                },
                modifier = modifierFillMaxWidth
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = uiState.username,
                onValueChange = { onSignupEvent(SignupEvent.OnUsernameChanged(it)) },
                label = { Text("Nombre usuario") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "icono nombre usuario"
                    )
                },
                modifier = modifierFillMaxWidth
            )
            Spacer(Modifier.height(8.dp))
            PasswordTextField(
                uiState.password,
                onPasswordValueChanged = { onSignupEvent(SignupEvent.OnPasswordChanged(it)) },
                modifier = modifierFillMaxWidth
            )
            Spacer(Modifier.height(8.dp))
            PasswordTextField(
                uiState.passwordRepeat,
                onPasswordValueChanged = { onSignupEvent(SignupEvent.OnPasswordRepeatChanged(it)) },
                label = "Repetir contraseña",
                errorMessage = if (true) "Las contraseñas no coinciden" else null,
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
    var signupUiState by remember   { mutableStateOf(SignupUiState()) }
    val onSignupEvent: (SignupEvent) -> Unit = { event ->
        when (event) {
            is SignupEvent.OnEmailChanged -> {
                // TODO: Run validator
                signupUiState = signupUiState.copy(
                    email = event.email
                )
            }
            is SignupEvent.OnUsernameChanged -> {
                // TODO: Run validator
                signupUiState = signupUiState.copy(
                    username = event.username
                )
            }
            is SignupEvent.OnPasswordChanged -> {
                // TODO: Run validator
                signupUiState = signupUiState.copy(
                    password = event.password
                )
            }
            is SignupEvent.OnPasswordRepeatChanged -> {
                // TODO: Run validator
                signupUiState = signupUiState.copy(
                    passwordRepeat = event.passwordRepeat
                )
            }
            SignupEvent.OnAcceptToCChanged -> {
                // TODO: Run validator
                signupUiState = signupUiState.copy(
                    acceptToC = !signupUiState.acceptToC
                )
            }
            SignupEvent.OnSignup -> { }
        }
    }
    LoginTheme {
        SignupScreen(signupUiState, onSignupEvent)
    }
}


