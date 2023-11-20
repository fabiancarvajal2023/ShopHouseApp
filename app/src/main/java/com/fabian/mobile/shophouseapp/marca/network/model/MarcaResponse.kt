package com.fabian.mobile.shophouseapp.marca.network.model

import com.google.gson.annotations.SerializedName

data class MarcaResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val nombre: String
)