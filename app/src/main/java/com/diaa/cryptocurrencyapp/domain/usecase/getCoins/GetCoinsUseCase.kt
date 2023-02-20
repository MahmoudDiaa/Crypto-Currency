package com.diaa.cryptocurrencyapp.domain.usecase.getCoins

import android.util.Log
import com.diaa.cryptocurrencyapp.common.Resource
import com.diaa.cryptocurrencyapp.data.remote.dto.toCoin
import com.diaa.cryptocurrencyapp.domain.model.Coin
import com.diaa.cryptocurrencyapp.domain.repo.CoinRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val repo: CoinRepo) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = repo.getCoins().map { it.toCoin() }
            emit(Resource.Success<List<Coin>>(coins))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Coin>>(e.message() ?: "An Unexpected error"))
        } catch (e: IOException) {

            emit(Resource.Error<List<Coin>>("Check Internet Connection"))
        }
    }


}