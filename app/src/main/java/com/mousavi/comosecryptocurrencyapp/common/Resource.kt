package com.mousavi.comosecryptocurrencyapp.common

sealed class Resource<T>(val data: T?, val error: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(data: T? = null, error: String) : Resource<T>(data, error)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}
