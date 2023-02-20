package com.diaa.cryptocurrencyapp.data.remote

import com.diaa.cryptocurrencyapp.data.remote.dto.CoinDetailsDto
import com.diaa.cryptocurrencyapp.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {
    @GET("coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("coins/{id}")
    suspend fun getCoin(@Path("id") coinID: String): CoinDetailsDto

}