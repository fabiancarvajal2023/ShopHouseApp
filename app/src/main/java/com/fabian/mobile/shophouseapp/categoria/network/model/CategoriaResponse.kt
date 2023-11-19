package com.fabian.mobile.shophouseapp.categoria.network.model

import com.google.gson.annotations.SerializedName

data class CategoriaResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("imagen") val imagen: String
)