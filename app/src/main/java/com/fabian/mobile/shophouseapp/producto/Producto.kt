package com.fabian.mobile.shophouseapp.producto

import com.fabian.mobile.shophouseapp.categoria.Categoria
import com.fabian.mobile.shophouseapp.marca.Marca

data class Producto(
    val id: Long,
    val marca: Marca,
    val categoria: Categoria,
    val imagen: String,
    val nombre: String,
    val codigoBarras: String,
    val precioVenta: Int,
    val precioCompra: String,
)