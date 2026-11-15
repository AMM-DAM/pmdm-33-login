package com.pmdm.login.ui.features.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.pmdmiesbalmis.components.ui.composables.CheckboxWithLabel
import com.pmdm.login.ui.components.PasswordTextField
import com.pmdm.login.ui.theme.LoginTheme

@Composable
fun LoginForm(
    email: String,
    onEmailValueChanged: (String) -> Unit,
    password: String,
    onPasswordValueChanged: (String) -> Unit,
    recordarSesion: Boolean,
    onRecordarSesionChanged: (Boolean) -> Unit,
    onLogin: () -> Unit
) {
    val modifierFillMaxWidth = Modifier.fillMaxWidth()
    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = onEmailValueChanged,
                label = { Text("Correo electrónico") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "email"
                    )
                },
                modifier = modifierFillMaxWidth
            )
            Spacer(Modifier.height(8.dp))
            PasswordTextField(
                password,
                onPasswordValueChanged,
                modifier = modifierFillMaxWidth
            )
            Spacer(Modifier.height(16.dp))
            CheckboxWithLabel(
                label = "Recordar sesión",
                checkedState = recordarSesion,
                onStateChange = onRecordarSesionChanged,
            )
            Spacer(Modifier.height(16.dp))
            Button(onClick = onLogin, modifierFillMaxWidth) {
                Text("Iniciar sesión")
            }
        }
    }
}


@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var recordarSesion by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(20.dp)) {
        LoginForm(
            email = email,
            password = password,
            onEmailValueChanged = { email = it },
            onPasswordValueChanged = { password = it },
            recordarSesion = recordarSesion,
            onRecordarSesionChanged = { recordarSesion = it },
            onLogin = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginTheme {
        LoginScreen()
    }
}