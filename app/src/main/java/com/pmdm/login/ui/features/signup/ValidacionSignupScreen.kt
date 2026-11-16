package com.pmdm.login.ui.features.signup

import com.github.pmdmiesbalmis.components.validacion.Validacion

data class ValidacionSignupScreen(
    var mensajeErrorGlobal: String? = "",
    var validacionEmail: Validacion = object: Validacion {},
    var validacionUsername: Validacion = object : Validacion {},
    var validacionPassword: Validacion = object : Validacion {},
    var validacionPasswordRepeat: Validacion = object : Validacion {}
) : Validacion {
    override val hayError: Boolean
        get() = validacionEmail.hayError
                || validacionUsername.hayError
                || validacionPassword.hayError
                || validacionPasswordRepeat.hayError

    override val mensajeError: String?
        get() = when {
            validacionEmail.hayError -> validacionEmail.mensajeError
            validacionUsername.hayError -> validacionUsername.mensajeError
            validacionPassword.hayError -> validacionPassword.mensajeError
            validacionPasswordRepeat.hayError -> validacionPasswordRepeat.mensajeError
            else -> mensajeErrorGlobal
        }
}