package com.pmdm.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.pmdm.login.data.PiezaRepository
import com.pmdm.login.ui.features.presentacion.PresentacionScreen
import com.pmdm.login.ui.theme.LoginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  enableEdgeToEdge()
        setContent {
            LoginTheme() {
                val piezaMostrar by remember {
                    mutableStateOf(PiezaRepository().getPiezas().random())
                }
                PresentacionScreen(
                    piezaMostrar = piezaMostrar,
                    onCrearCuentaClick = {},
                    onIniciarSesionClick = {}
                )
            }
        }
    }
}