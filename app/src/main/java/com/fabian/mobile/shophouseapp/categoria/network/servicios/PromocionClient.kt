package com.fabian.mobile.shophouseapp.categoria.network.servicios

import com.fabian.mobile.shophouseapp.categoria.network.model.PromocionResponse
import com.fabian.mobile.shophouseapp.network.RetrofitClient
import retrofit2.http.GET

interface PromocionClient {

    companion object {
        val instance = RetrofitClient.instance.create(PromocionClient::class.java)
    }

    @GET("promocion")
    suspend fun getPromociones(): List<PromocionResponse>
}