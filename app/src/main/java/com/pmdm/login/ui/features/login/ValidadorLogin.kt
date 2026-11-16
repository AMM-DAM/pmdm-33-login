package com.pmdm.login.ui.features.login

import com.github.pmdmiesbalmis.components.validacion.Validador
import com.github.pmdmiesbalmis.components.validacion.ValidadorCompuesto
import com.github.pmdmiesbalmis.components.validacion.validadores.ValidadorCorreo
import com.github.pmdmiesbalmis.components.validacion.validadores.ValidadorLongitudMinimaTexto
import com.github.pmdmiesbalmis.components.validacion.validadores.ValidadorTextoNoVacio

class ValidadorLogin(
    var mensajeErrorGlobal: String? = "Por favor, revise los campos marcados"
) : Validador<LoginUiState> {

    val validadorCorreo = ValidadorCompuesto<String>()
        .add(ValidadorTextoNoVacio("El correo no puede estar vacío"))
        .add(ValidadorCorreo("El correo electrónico no es válido"))

    val validadorContrasena = ValidadorCompuesto<String>()
        .add(ValidadorTextoNoVacio("La clave no puede estar vacía"))
        .add(ValidadorLongitudMinimaTexto(8, "La clave debe tener al menos 8 caracteres"))

    override fun valida(datos: LoginUiState): ValidacionLoginUiState {
        val valCorreo = validadorCorreo.valida(datos.email)
        val valContrasena = validadorContrasena.valida(datos.password)

        return ValidacionLoginUiState(
            mensajeErrorGlobal = mensajeErrorGlobal,
            validacionCorreo = valCorreo,
            validacionContrasena = valContrasena
        )
    }
}