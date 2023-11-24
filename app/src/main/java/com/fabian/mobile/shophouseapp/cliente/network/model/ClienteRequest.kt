package com.fabian.mobile.shophouseapp.cliente.network.model

import com.google.gson.annotations.SerializedName

data class ClienteRequest(
    @SerializedName("tipoIdentificacion") val tipoIdentificacion: Int,
    @SerializedName("identificacion") val identificacion: String,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("direccion") val direccion: String? = null,
    @SerializedName("telefono") val telefono: String? = null
)