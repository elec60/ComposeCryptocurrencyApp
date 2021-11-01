package com.mousavi.comosecryptocurrencyapp.di

import com.mousavi.comosecryptocurrencyapp.common.Constants
import com.mousavi.comosecryptocurrencyapp.data.remote.CoinApi
import com.mousavi.comosecryptocurrencyapp.data.repository.CoinsRepositoryImpl
import com.mousavi.comosecryptocurrencyapp.domain.repository.CoinsRepository
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
    fun provideRetrofitApi(): CoinApi {
        return Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinApi): CoinsRepository {
        return CoinsRepositoryImpl(api)
    }

}