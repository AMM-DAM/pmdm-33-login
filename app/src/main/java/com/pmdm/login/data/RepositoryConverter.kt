package com.pmdm.login.data

import com.pmdm.login.data.mocks.PiezaMock

import com.pmdm.login.model.Pieza

fun Pieza.aPiezaMock(): PiezaMock= PiezaMock(
    id = id,
    titulo = titulo,
    categoria = categoria,
    epoca = epoca,
    autor = autor,
    valorEstimado = valorEstimado,
    notas = notas,
    foto = foto
)

fun PiezaMock.aPieza(): Pieza = Pieza(
    id = id,
    titulo = titulo,
    categoria = categoria,
    epoca = epoca,
    autor = autor,
    valorEstimado = valorEstimado,
    notas = notas,
    foto = foto
)
