package com.mousavi.comosecryptocurrencyapp.domain.usecases.get_coin

import com.mousavi.comosecryptocurrencyapp.common.Resource
import com.mousavi.comosecryptocurrencyapp.data.remote.dto.toCoinDetail
import com.mousavi.comosecryptocurrencyapp.domain.model.CoinDetails
import com.mousavi.comosecryptocurrencyapp.domain.repository.CoinsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private var repository: CoinsRepository,
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetails>> = flow {
        try {
            emit(Resource.Loading<CoinDetails>())
            val coinDetails = repository.getCoinDetails(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetails>(coinDetails))
        } catch (e: HttpException) {
            emit(Resource.Error<CoinDetails>(error = e.message ?: "Error occurred"))
        } catch (e: IOException) {
            emit(
                Resource.Error<CoinDetails>(error = e.message ?: "Check your internet connection!")
            )
        } catch (e: Exception) {
            emit(Resource.Error<CoinDetails>(error = e.message ?: "Unknown error!"))
        }
    }
}
