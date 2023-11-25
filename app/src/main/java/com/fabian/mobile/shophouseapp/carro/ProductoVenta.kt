package com.fabian.mobile.shophouseapp.carro

import com.google.gson.annotations.SerializedName

data class ProductoVenta(
    val producto: Long,
    val cantidad: Int,
    val precioVenta: Int,
    val precioCompra: Int
)
