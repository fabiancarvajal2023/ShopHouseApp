package com.fabian.mobile.shophouseapp.cliente.network.servicios

import com.fabian.mobile.shophouseapp.cliente.network.model.ClienteRequest
import com.fabian.mobile.shophouseapp.cliente.network.model.LoginRequest
import com.fabian.mobile.shophouseapp.cliente.network.model.LoginResponse
import com.fabian.mobile.shophouseapp.network.RetrofitClient
import retrofit2.http.Body
import retrofit2.http.POST

interface ClienteClient {

    companion object {
        val instance = RetrofitClient.instance.create(ClienteClient::class.java)
    }

    @POST("cliente/registrar")
    suspend fun registrar(@Body clienteRequest: ClienteRequest): Long

    @POST("cliente/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse
}