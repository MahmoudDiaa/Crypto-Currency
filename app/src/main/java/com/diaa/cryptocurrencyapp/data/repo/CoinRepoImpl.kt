package com.diaa.cryptocurrencyapp.data.repo

import com.diaa.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.diaa.cryptocurrencyapp.data.remote.dto.CoinDetailsDto
import com.diaa.cryptocurrencyapp.data.remote.dto.CoinDto
import com.diaa.cryptocurrencyapp.domain.repo.CoinRepo
import javax.inject.Inject

class CoinRepoImpl @Inject constructor(private val coinPaprikaApi: CoinPaprikaApi) : CoinRepo {
    override suspend fun getCoins(): List<CoinDto> = coinPaprikaApi.getCoins()
    override suspend fun getCoinByID(coinId: String): CoinDetailsDto =
        coinPaprikaApi.getCoin(coinID = coinId)
}