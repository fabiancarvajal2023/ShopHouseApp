package com.fabian.mobile.shophouseapp.cliente.network.servicios

import com.fabian.mobile.shophouseapp.categoria.network.model.CategoriaResponse
import com.fabian.mobile.shophouseapp.cliente.network.model.TipoIdentificacionResponse
import com.fabian.mobile.shophouseapp.network.RetrofitClient
import retrofit2.http.GET

interface TipoIdentificacionClient {

    companion object {
        val instance = RetrofitClient.instance.create(TipoIdentificacionClient::class.java)
    }

    @GET("tipoidentificacion")
    suspend fun getTiposIdentificacion(): List<TipoIdentificacionResponse>
}