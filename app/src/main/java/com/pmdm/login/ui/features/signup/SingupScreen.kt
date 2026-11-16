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
fun SignupForm(
    email: String,
    onEmailChanged: (String) -> Unit,
    username: String,
    onUsernameChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    passwordRepeat: String,
    onPasswordRepeatChanged: (String) -> Unit,
    isRepeatPasswordMatch: Boolean = true,
    acceptToC: Boolean,
    onAcceptToCChanged: (Boolean) -> Unit,
    onSignup: () -> Unit
) {
    val modifierFillMaxWidth = Modifier.fillMaxWidth()
    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(),
    ) {
        Column(
            Modifier.padding(8.dp)
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = onEmailChanged,
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
                value = username,
                onValueChange = onUsernameChanged,
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
                password,
                onPasswordChanged,
                modifier = modifierFillMaxWidth
            )
            Spacer(Modifier.height(8.dp))
            PasswordTextField(
                passwordRepeat,
                onPasswordRepeatChanged,
                label = "Repetir contraseña",
                errorMessage = if (!isRepeatPasswordMatch) "Las contraseñas no coinciden" else null,
                modifier = modifierFillMaxWidth
            )
            Spacer(Modifier.height(16.dp))
            CheckboxWithLabel(
                label = "Acepto los términos y condiciones",
                checkedState = acceptToC,
                onStateChange = onAcceptToCChanged,
            )
            Spacer(Modifier.height(16.dp))
            Button(onClick = onSignup, modifierFillMaxWidth) {
                Text("Crear cuenta")
            }
        }
    }
}

@Composable
fun SignupScreen(
    email: String,
    onEmailChanged: (String) -> Unit,
    username: String,
    onUsernameChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    passwordRepeat: String,
    onPasswordRepeatChanged: (String) -> Unit,
    isRepeatPasswordMatch: Boolean = true,
    acceptToC: Boolean,
    onAcceptToCChanged: (Boolean) -> Unit,
    onSignup: () -> Unit
) {
    Column(modifier = Modifier.padding(20.dp)) {
        SignupForm(
            email = email,
            onEmailChanged = onEmailChanged,
            username = username,
            onUsernameChanged = onUsernameChanged,
            password = password,
            onPasswordChanged = onPasswordChanged,
            passwordRepeat = passwordRepeat,
            onPasswordRepeatChanged = onPasswordRepeatChanged,
            isRepeatPasswordMatch = isRepeatPasswordMatch,
            acceptToC = acceptToC,
            onAcceptToCChanged = onAcceptToCChanged,
            onSignup = onSignup
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignupScreenPreview() {
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordRepeat by remember { mutableStateOf("") }
    var isRepeatPasswordMatch by remember { mutableStateOf(true) }
    var acceptToC by remember { mutableStateOf(false) }

    LoginTheme {
        SignupScreen(
            email = email,
            onEmailChanged = { email = it },
            username = username,
            onUsernameChanged = { username = it },
            password = password,
            onPasswordChanged = {
                password = it
                isRepeatPasswordMatch = (it == passwordRepeat)
            },
            passwordRepeat = passwordRepeat,
            onPasswordRepeatChanged = {
                passwordRepeat = it
                isRepeatPasswordMatch = (password == it)
            },
            isRepeatPasswordMatch = isRepeatPasswordMatch,
            acceptToC = acceptToC,
            onAcceptToCChanged = { acceptToC = it },
            onSignup = {}
        )
    }
}


