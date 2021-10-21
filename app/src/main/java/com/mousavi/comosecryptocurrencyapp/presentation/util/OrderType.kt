package com.mousavi.comosecryptocurrencyapp.presentation.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}
