package com.fabian.mobile.shophouseapp.categoria

data class Promocion(
    val id: Int,
    val categoria: Categoria,
    val porcentaje: Int,
    val articuloRequerido: Boolean
)