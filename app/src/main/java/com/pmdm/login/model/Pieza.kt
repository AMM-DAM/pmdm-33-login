package com.pmdm.login.model

data class Pieza(
    val id: Int = 0,
    val titulo: String,
    val categoria: CategoriaColeccion,
    val epoca: String,
    val autor: String,
    val valorEstimado: Double,
    val notas: String,
    val foto: String
)
