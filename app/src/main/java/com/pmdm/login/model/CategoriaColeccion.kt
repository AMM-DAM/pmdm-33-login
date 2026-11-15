package com.pmdm.login.model

enum class CategoriaColeccion(
    val nombreVisible: String,
    val claveFiltro: String
) {
    TODAS("Todas", "todas"),
    ALTA_HOROLOGIA("Relojería", "relojes"),
    PINTURA("Pintura", "pintura"),
    NUMISMATICA("Numismática", "numismatica"),
    ESCULTURA("Escultura", "escultura"),
    LIBROS_RAROS("Rarezas", "rarezas"),
    ARTES_DECORATIVAS("Artes Decorativas", "decorativas");

    companion object {
        fun desdeString(valor: String): CategoriaColeccion {
            return entries.firstOrNull { 
                it.name.equals(valor, ignoreCase = true) || 
                it.nombreVisible.equals(valor, ignoreCase = true) ||
                it.claveFiltro.equals(valor, ignoreCase = true)
            } ?: ALTA_HOROLOGIA
        }
    }
}
