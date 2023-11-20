package com.fabian.mobile.shophouseapp.producto.network.servicios

import com.fabian.mobile.shophouseapp.network.RetrofitClient
import com.fabian.mobile.shophouseapp.producto.network.model.ProductoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductoClient {
    companion object {
        val instance = RetrofitClient.instance.create(ProductoClient::class.java)
    }

    @GET("producto")
    suspend fun getProductos(): List<ProductoResponse>

    @GET("producto/{nombre}")
    suspend fun getProductosBuscarPorNombre(@Path("nombre") nombre: String): List<ProductoResponse>

    @GET("producto/categoria/{categoriaId}")
    suspend fun getProductosBuscarPorCategoria(@Path("categoriaId") categoriaId: Int): List<ProductoResponse>

    @GET("producto/categoria/{categoriaId}/{nombre}")
    suspend fun getProductosBuscarPorCategoriaYNombre(
        @Path("categoriaId") categoriaId: Int,
        @Path("nombre") nombre: String
    ): List<ProductoResponse>
}