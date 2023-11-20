package com.fabian.mobile.shophouseapp.categoria.network.model

import com.google.gson.annotations.SerializedName

data class PromocionResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("categoria") val categoria: CategoriaResponse,
    @SerializedName("porcentaje") val porcentaje: Int,
    @SerializedName("articuloRequerido") val articuloRequerido: Boolean
)