package com.mousavi.comosecryptocurrencyapp.data.repository

import com.mousavi.comosecryptocurrencyapp.data.remote.CoinApi
import com.mousavi.comosecryptocurrencyapp.data.remote.dto.CoinDetailsDto
import com.mousavi.comosecryptocurrencyapp.data.remote.dto.CoinDto
import com.mousavi.comosecryptocurrencyapp.domain.repository.CoinsRepository
import javax.inject.Inject

class CoinsRepositoryImpl @Inject constructor(
    private val api: CoinApi,
) : CoinsRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinDetails(coinId: String): CoinDetailsDto {
        return api.getCoinDetails(coinId)
    }
}