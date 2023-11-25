package com.fabian.mobile.shophouseapp.carro.network.model

import com.google.gson.annotations.SerializedName

data class VentaRequest(
    @SerializedName("fecha") val fecha: Long,
    @SerializedName("cliente") val cliente: Long,
    @SerializedName("ventaTotal") val ventaTotal: Int,
    @SerializedName("ventaTotalCompra") val ventaTotalCompra: Int,
    @SerializedName("productoVenta") val productoVenta: List<ProductoVentaRequest>
)
