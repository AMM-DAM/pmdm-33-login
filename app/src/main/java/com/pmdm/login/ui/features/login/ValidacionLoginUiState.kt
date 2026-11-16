package com.pmdm.login.ui.features.login

import com.github.pmdmiesbalmis.components.validacion.Validacion

data class ValidacionLoginUiState(
    val mensajeErrorGlobal: String? = null,
    val validacionCorreo: Validacion = object : Validacion {},
    val validacionContrasena: Validacion = object : Validacion {}
) : Validacion {
    override val hayError: Boolean
        get() = validacionCorreo.hayError || validacionContrasena.hayError

    override val mensajeError: String?
        get() = when {
            validacionCorreo.hayError -> validacionCorreo.mensajeError
            validacionContrasena.hayError -> validacionContrasena.mensajeError
            else -> mensajeErrorGlobal
        }
}