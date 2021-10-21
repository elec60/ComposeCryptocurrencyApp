package com.mousavi.comosecryptocurrencyapp.presentation.util

data class UserEvent(
    val isOrderSectionVisible: Boolean = false,
    val order: CoinOrder = CoinOrder.Rank(OrderType.Ascending)
)
