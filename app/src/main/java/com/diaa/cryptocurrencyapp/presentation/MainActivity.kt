package com.diaa.cryptocurrencyapp.presentation

import Screen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text


import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diaa.cryptocurrencyapp.common.Constants
import com.diaa.cryptocurrencyapp.presentation.coinDetails.CoinDetailScreen
import com.diaa.cryptocurrencyapp.presentation.coinList.CoinListScreen
import com.diaa.cryptocurrencyapp.presentation.coinList.CoinsViewModel
import com.diaa.cryptocurrencyapp.presentation.theme.CryptoCurrencyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoCurrencyAppTheme {
                // A surface container using the 'background' color from the theme
                AnimatedVisibility(visible = true) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = Screen.CoinListScreen.route,
                        ) {
                            composable(
                                route = Screen.CoinListScreen.route
                            ) {
                                CoinListScreen(navController)
                            }
                            composable(
                                route = Screen.CoinDetailScreen.route + "/{${Constants.PARAM_COIN_ID}}"
                            ) {
                                CoinDetailScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}