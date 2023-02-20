package com.diaa.cryptocurrencyapp.domain.usecase.getCoin

import com.diaa.cryptocurrencyapp.common.Resource
import com.diaa.cryptocurrencyapp.data.remote.dto.toCoin
import com.diaa.cryptocurrencyapp.data.remote.dto.toCoinDetail
import com.diaa.cryptocurrencyapp.domain.model.Coin
import com.diaa.cryptocurrencyapp.domain.model.CoinDetail
import com.diaa.cryptocurrencyapp.domain.repo.CoinRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(private val repo: CoinRepo) {

    operator fun invoke(coinID:String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repo.getCoinByID(coinID).toCoinDetail()
            emit(Resource.Success(coin))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message() ?: "An Unexpected error"))
        } catch (e: IOException) {

            emit(Resource.Error("Check Internet Connection"))
        }
    }


}