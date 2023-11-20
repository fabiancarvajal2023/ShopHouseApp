package com.fabian.mobile.shophouseapp.producto.network.model

import com.fabian.mobile.shophouseapp.categoria.network.model.CategoriaResponse
import com.fabian.mobile.shophouseapp.marca.network.model.MarcaResponse
import com.google.gson.annotations.SerializedName

data class ProductoResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("marca") val marca: MarcaResponse,
    @SerializedName("categoria") val categoria: CategoriaResponse,
    @SerializedName("imagen") val imagen: String,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("codigoBarras") val codigoBarras: String,
    @SerializedName("precioVenta") val precioVenta: Int,
    @SerializedName("precioCompra") val precioCompra: String,
)
