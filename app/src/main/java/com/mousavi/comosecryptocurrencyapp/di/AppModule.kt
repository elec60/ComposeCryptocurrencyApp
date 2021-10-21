package com.mousavi.comosecryptocurrencyapp.di

import com.google.gson.Gson
import com.mousavi.comosecryptocurrencyapp.data.remote.CoinAPi
import com.mousavi.comosecryptocurrencyapp.data.repository.CoinsRepositoryImpl
import com.mousavi.comosecryptocurrencyapp.domain.repository.CoinsRepository
import com.mousavi.comosecryptocurrencyapp.domain.usecases.GetCoins
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitApi(): CoinAPi {
        return Retrofit
            .Builder()
            .baseUrl("https://api.coinpaprika.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinAPi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(repo: CoinsRepositoryImpl): CoinsRepository {
        return repo
    }

    @Provides
    @Singleton
    fun provideGetCoinUseCase(repository: CoinsRepository): GetCoins {
        return GetCoins(repository)
    }
}