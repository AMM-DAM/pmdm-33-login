package com.pmdm.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.pmdm.login.data.PiezaRepository
import com.pmdm.login.ui.features.login.LoginEvent
import com.pmdm.login.ui.features.login.LoginScreen
import com.pmdm.login.ui.features.login.LoginUiState
import com.pmdm.login.ui.features.login.ValidadorLogin
import com.pmdm.login.ui.features.presentacion.PresentacionScreen
import com.pmdm.login.ui.features.signup.SignupEvent
import com.pmdm.login.ui.features.signup.SignupScreen
import com.pmdm.login.ui.features.signup.SignupUiState
import com.pmdm.login.ui.features.signup.ValidadorSignup
import com.pmdm.login.ui.theme.LoginTheme

enum class Pantalla {
    PRESENTACION,
    LOGIN,
    SIGNUP
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginTheme {
                GestorColeccionesApp()
            }
        }
    }
}

@Composable
fun GestorColeccionesApp() {
    var pantallaActual by remember { mutableStateOf(Pantalla.PRESENTACION) }

    val piezaMostrar by remember {
        mutableStateOf(PiezaRepository().getPiezas().random())
    }

    val validadorLogin = remember { ValidadorLogin() }
    var loginUiState by remember { mutableStateOf(LoginUiState()) }

    val validadorSignup = remember { ValidadorSignup() }
    var signupUiState by remember { mutableStateOf(SignupUiState()) }

    when (pantallaActual) {
        Pantalla.PRESENTACION -> {
            PresentacionScreen(
                piezaMostrar = piezaMostrar,
                onCrearCuentaClick = { pantallaActual = Pantalla.SIGNUP },
                onIniciarSesionClick = { pantallaActual = Pantalla.LOGIN }
            )
        }
        Pantalla.LOGIN -> {
            LoginScreen(
                loginUiState = loginUiState,
                onLoginEvent = { event ->
                    when (event) {
                        is LoginEvent.OnEmailChanged -> {
                            val validacionCorreo = validadorLogin.validadorCorreo.valida(event.email)
                            loginUiState = loginUiState.copy(
                                email = event.email,
                                validacion = loginUiState.validacion.copy(validacionCorreo = validacionCorreo)
                            )
                        }
                        is LoginEvent.OnPasswordChanged -> {
                            val validacionContrasena = validadorLogin.validadorContrasena.valida(event.password)
                            loginUiState = loginUiState.copy(
                                password = event.password,
                                validacion = loginUiState.validacion.copy(validacionContrasena = validacionContrasena)
                            )
                        }
                        LoginEvent.OnRecordarSesionChanged -> {
                            loginUiState = loginUiState.copy(recordarSesion = !loginUiState.recordarSesion)
                        }
                        LoginEvent.OnLogin -> {
                            loginUiState = loginUiState.copy(
                                validacion = validadorLogin.valida(loginUiState)
                            )
                        }
                        LoginEvent.OnNavigateToSignup -> {
                            pantallaActual = Pantalla.SIGNUP
                        }
                    }
                }
            )
        }
        Pantalla.SIGNUP -> {
            SignupScreen(
                uiState = signupUiState,
                onSignupEvent = { event ->
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
                        }
                        SignupEvent.OnNavigateToLogin -> {
                            pantallaActual = Pantalla.LOGIN
                        }
                    }
                }
            )
        }
    }
}
