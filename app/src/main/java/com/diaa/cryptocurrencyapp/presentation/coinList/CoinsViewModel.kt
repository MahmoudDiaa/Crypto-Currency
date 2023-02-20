package com.diaa.cryptocurrencyapp.presentation.coinList

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diaa.cryptocurrencyapp.common.Resource
import com.diaa.cryptocurrencyapp.domain.usecase.getCoins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

private const val TAG = "CoinsViewModel"
@HiltViewModel
class CoinsViewModel @Inject constructor(private val getCoinsUseCase: GetCoinsUseCase) :
    ViewModel() {

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

init {
    getCoins()
}
    private fun getCoins() {

        getCoinsUseCase().onEach { result ->

            when (result) {
                is Resource.Success -> {
                    _state.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value =
                        CoinListState(error = result.message ?: "An unexpected error happened")
                }
                is Resource.Loading -> {

                    _state.value = CoinListState(isLoading = true)
                }

            }
        }.launchIn(viewModelScope)

    }

}