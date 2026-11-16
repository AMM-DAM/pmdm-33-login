package com.pmdm.login.ui.features.signup

import com.github.pmdmiesbalmis.components.validacion.Validacion
import com.github.pmdmiesbalmis.components.validacion.Validador
import com.github.pmdmiesbalmis.components.validacion.ValidadorCompuesto
import com.github.pmdmiesbalmis.components.validacion.validadores.ValidadorCorreo
import com.github.pmdmiesbalmis.components.validacion.validadores.ValidadorLongitudMaximaTexto
import com.github.pmdmiesbalmis.components.validacion.validadores.ValidadorLongitudMinimaTexto
import com.github.pmdmiesbalmis.components.validacion.validadores.ValidadorTextoNoVacio
import com.pmdm.login.ui.validation.validadores.ValidadorCoincidePassword

class ValidadorSignup(
    var mensajeErrorGlobal: String? = "Por favor, revise los campos marcados"
) : Validador<SignupUiState> {

    val validadorEmail = ValidadorCompuesto<String>()
        .add(ValidadorTextoNoVacio())
        .add(ValidadorCorreo())

    val validadorUsername = ValidadorCompuesto<String>()
        .add(ValidadorTextoNoVacio())
        .add(ValidadorLongitudMinimaTexto(3))
        .add(ValidadorLongitudMaximaTexto(16))

    val validadorPassword = ValidadorCompuesto<String>()
        .add(ValidadorTextoNoVacio())
        .add(ValidadorLongitudMinimaTexto(6))

    val validadorPasswordRepeat = ValidadorCoincidePassword()

    override fun valida(datos: SignupUiState): ValidacionSignupScreen {
        val validacionEmail = validadorEmail.valida(datos.email)
        val validacionUsername = validadorUsername.valida(datos.username)
        val validacionPassword = validadorPassword.valida(datos.password)
        val validacionPasswordRepeat = validadorPasswordRepeat.valida(datos.passwordRepeat, datos.password)

        return ValidacionSignupScreen(
            mensajeErrorGlobal = mensajeErrorGlobal,
            validacionEmail = validacionEmail,
            validacionUsername = validacionUsername,
            validacionPassword = validacionPassword,
            validacionPasswordRepeat = validacionPasswordRepeat
        )
    }

}