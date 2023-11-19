package com.fabian.mobile.shophouseapp.categoria.network.servicios

import com.fabian.mobile.shophouseapp.categoria.network.model.CategoriaResponse
import com.fabian.mobile.shophouseapp.network.RetrofitClient
import retrofit2.http.GET

interface CategoriaClient {

    companion object {
        val instance = RetrofitClient.instance.create(CategoriaClient::class.java)
    }


    @GET("categoria")
    suspend fun getCategoria(): List<CategoriaResponse>

}