package com.mousavi.comosecryptocurrencyapp.domain.model

import com.mousavi.comosecryptocurrencyapp.data.remote.dto.*

data class CoinDetails(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<TeamMember>
)
