package com.mousavi.comosecryptocurrencyapp.domain.repository

import com.mousavi.comosecryptocurrencyapp.data.remote.dto.CoinDetailsDto
import com.mousavi.comosecryptocurrencyapp.data.remote.dto.CoinDto

interface CoinsRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinDetails(): CoinDetailsDto
}