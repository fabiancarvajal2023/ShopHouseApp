package com.fabian.mobile.shophouseapp.cliente.network.model

import com.google.gson.annotations.SerializedName

data class TipoIdentificacionResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("abreviatura") val abreviatura: String
)