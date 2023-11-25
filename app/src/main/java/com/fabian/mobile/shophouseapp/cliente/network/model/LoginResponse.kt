package com.fabian.mobile.shophouseapp.cliente.network.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("nombre") val nombre: String,
    @SerializedName("id") val id: Long
)