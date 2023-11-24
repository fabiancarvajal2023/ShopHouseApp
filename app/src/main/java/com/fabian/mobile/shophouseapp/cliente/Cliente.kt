package com.fabian.mobile.shophouseapp.cliente

data class Cliente(
    val tipoIdentificacion: Int,
    val identificacion: String,
    val nombre: String,
    val email: String,
    val password: String,
    val direccion: String? = null,
    val telefono: String? = null
)