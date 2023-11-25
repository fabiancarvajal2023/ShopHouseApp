package com.fabian.mobile.shophouseapp.carro.network.model

import com.google.gson.annotations.SerializedName

data class ProductoVentaRequest(
    @SerializedName("producto") val producto: Long,
    @SerializedName("cantidad") val cantidad: Int,
    @SerializedName("precioVenta") val precioVenta: Int,
    @SerializedName("precioCompra") val precioCompra: Int
)
