package com.diaa.cryptocurrencyapp.di

import com.diaa.cryptocurrencyapp.common.Constants
import com.diaa.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.diaa.cryptocurrencyapp.data.repo.CoinRepoImpl
import com.diaa.cryptocurrencyapp.domain.repo.CoinRepo
import com.diaa.cryptocurrencyapp.domain.usecase.getCoin.GetCoinUseCase
import com.diaa.cryptocurrencyapp.domain.usecase.getCoins.GetCoinsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient = OkHttpClient
        .Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): CoinPaprikaApi =
        retrofit.create(CoinPaprikaApi::class.java)


    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaApi): CoinRepo = CoinRepoImpl(api)

    @Provides
    fun provideCoinsUseCase(coinRepo: CoinRepo)=GetCoinsUseCase(coinRepo)

    @Provides
    fun provideCoinDetailUseCase(coinRepo: CoinRepo)= GetCoinUseCase(coinRepo)

}