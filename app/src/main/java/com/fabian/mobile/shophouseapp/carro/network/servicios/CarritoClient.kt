package com.fabian.mobile.shophouseapp.carro.network.servicios

import com.fabian.mobile.shophouseapp.carro.network.model.VentaRequest
import com.fabian.mobile.shophouseapp.network.RetrofitClient
import retrofit2.http.Body
import retrofit2.http.POST

interface CarritoClient {
    companion object {
        val instance = RetrofitClient.instance.create(CarritoClient::class.java)
    }

    @POST("venta/registrar")
    suspend fun registrar(@Body ventaRequest: VentaRequest): Boolean
}