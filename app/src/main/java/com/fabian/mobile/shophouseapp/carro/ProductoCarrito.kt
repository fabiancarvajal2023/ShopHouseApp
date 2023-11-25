package com.fabian.mobile.shophouseapp.carro

import com.fabian.mobile.shophouseapp.producto.Producto

data class ProductoCarrito(
    val producto: Producto,
    val cantidad:Int
)
