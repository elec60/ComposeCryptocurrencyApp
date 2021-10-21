package com.mousavi.comosecryptocurrencyapp.presentation.util

sealed class CoinOrder(val orderType: OrderType) {
    class Name(orderType: OrderType) : CoinOrder(orderType)
    class Rank(orderType: OrderType) : CoinOrder(orderType)
    class Active(orderType: OrderType) : CoinOrder(orderType)
}