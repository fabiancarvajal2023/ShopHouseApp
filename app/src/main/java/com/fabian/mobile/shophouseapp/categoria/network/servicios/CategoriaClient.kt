package com.fabian.mobile.shophouseapp.categoria.network.servicios

import com.fabian.mobile.shophouseapp.categoria.network.model.CategoriaResponse
import com.fabian.mobile.shophouseapp.network.RetrofitClient
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CategoriaClient {

    companion object {
        val instance = RetrofitClient.instance.create(CategoriaClient::class.java)
    }


    @GET("categoria")
    suspend fun getCategoria(): List<CategoriaResponse>

    @GET("categoria/buscar/{nombre}")
    suspend fun getCategoriaPorNombre(@Path("nombre") nombre:String): List<CategoriaResponse>

}