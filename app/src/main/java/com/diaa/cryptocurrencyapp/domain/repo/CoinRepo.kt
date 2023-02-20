package com.diaa.cryptocurrencyapp.domain.repo

import com.diaa.cryptocurrencyapp.data.remote.dto.CoinDetailsDto
import com.diaa.cryptocurrencyapp.data.remote.dto.CoinDto
import retrofit2.http.Path

interface CoinRepo {

    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoinByID(@Path("coinId") coinId: String): CoinDetailsDto
}