package com.pmdm.login.ui.validation.validadores

import com.github.pmdmiesbalmis.components.validacion.Validacion
import com.github.pmdmiesbalmis.components.validacion.Validador

class ValidadorCoincidePassword(
    var mensajeError: String = "Las contraseñas no coinciden"
) : Validador<Pair<String, String>> {

    override fun valida(datos: Pair<String, String>): Validacion {
        val (passwordRepeat, passwordOriginal) = datos
        val hayError = passwordRepeat != passwordOriginal
        return object : Validacion {
            override val hayError: Boolean = hayError
            override val mensajeError: String? = if (hayError) this@ValidadorCoincidePassword.mensajeError else null
        }
    }

    fun valida(passwordRepeat: String, passwordOriginal: String): Validacion {
        return valida(Pair(passwordRepeat, passwordOriginal))
    }
}