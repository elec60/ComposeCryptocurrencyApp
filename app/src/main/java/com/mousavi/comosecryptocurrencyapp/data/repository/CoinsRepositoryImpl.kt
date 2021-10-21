package com.mousavi.comosecryptocurrencyapp.data.repository

import com.mousavi.comosecryptocurrencyapp.data.remote.CoinAPi
import com.mousavi.comosecryptocurrencyapp.data.remote.dto.CoinDetailsDto
import com.mousavi.comosecryptocurrencyapp.data.remote.dto.CoinDto
import com.mousavi.comosecryptocurrencyapp.domain.repository.CoinsRepository
import javax.inject.Inject

class CoinsRepositoryImpl @Inject constructor(
    private val api: CoinAPi,
) : CoinsRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinDetails(): CoinDetailsDto {
        return api.getCoinDetails("")
    }
}