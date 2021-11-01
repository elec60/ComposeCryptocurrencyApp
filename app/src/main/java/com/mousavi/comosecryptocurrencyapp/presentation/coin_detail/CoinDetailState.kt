package com.mousavi.comosecryptocurrencyapp.presentation.coin_detail

import com.mousavi.comosecryptocurrencyapp.domain.model.CoinDetails

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetails? = null,
    val error: String = ""
)