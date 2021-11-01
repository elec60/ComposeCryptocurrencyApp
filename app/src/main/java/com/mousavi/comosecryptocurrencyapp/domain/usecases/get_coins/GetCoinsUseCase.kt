package com.mousavi.comosecryptocurrencyapp.domain.usecases.get_coins

import com.mousavi.comosecryptocurrencyapp.common.Resource
import com.mousavi.comosecryptocurrencyapp.data.remote.dto.toCoin
import com.mousavi.comosecryptocurrencyapp.domain.model.Coin
import com.mousavi.comosecryptocurrencyapp.domain.repository.CoinsRepository
import com.mousavi.comosecryptocurrencyapp.presentation.util.CoinOrder
import com.mousavi.comosecryptocurrencyapp.presentation.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private var repository: CoinsRepository,
) {
    operator fun invoke(coinOrder: CoinOrder): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = repository.getCoins().map { it.toCoin() }
            val sorted: List<Coin>
            when (coinOrder.orderType) {
                OrderType.Ascending -> {
                    sorted = when (coinOrder) {
                        is CoinOrder.Active -> {
                            coins.sortedBy { it.isActive }
                        }
                        is CoinOrder.Name -> {
                            coins.sortedBy { it.name }
                        }
                        is CoinOrder.Rank -> {
                            coins.filter { it.rank > 0 }.sortedBy { it.rank }
                        }
                    }
                }
                OrderType.Descending -> {
                    sorted = when (coinOrder) {
                        is CoinOrder.Active -> {
                            coins.sortedByDescending { it.isActive }
                        }
                        is CoinOrder.Name -> {
                            coins.sortedByDescending { it.name }
                        }
                        is CoinOrder.Rank -> {
                            coins.sortedByDescending { it.rank }
                        }
                    }
                }
            }
            emit(Resource.Success<List<Coin>>(sorted))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Coin>>(error = e.message ?: "Error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Coin>>(error = e.message ?: "Check your internet connection!"))
        } catch (e: Exception) {
            emit(Resource.Error<List<Coin>>(error = e.message ?: "Unknown error!"))
        }
    }
}