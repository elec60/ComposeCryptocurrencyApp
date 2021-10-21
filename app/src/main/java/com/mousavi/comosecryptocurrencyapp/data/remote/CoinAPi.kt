package com.mousavi.comosecryptocurrencyapp.data.remote

import com.mousavi.comosecryptocurrencyapp.data.remote.dto.CoinDetailsDto
import com.mousavi.comosecryptocurrencyapp.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinAPi {

    @GET("v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("v1/coins/{id}")
    suspend fun getCoinDetails(@Path("id") id: String): CoinDetailsDto

}