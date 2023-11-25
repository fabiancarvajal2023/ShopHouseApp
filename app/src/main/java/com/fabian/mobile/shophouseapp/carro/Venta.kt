package com.fabian.mobile.shophouseapp.carro

data class Venta(
    val fecha: Long,
    val cliente: Long,
    val ventaTotal: Int,
    val ventaTotalCompra: Int,
    val productoVenta: List<ProductoVenta>
)