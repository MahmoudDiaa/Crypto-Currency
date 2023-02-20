package com.diaa.cryptocurrencyapp.presentation.coinDetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.diaa.cryptocurrencyapp.presentation.coinDetails.components.CoinTag
import com.diaa.cryptocurrencyapp.presentation.coinDetails.components.TeamListItem
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun CoinDetailScreen(viewModel: CoinDetailViewModel = hiltViewModel()) {


    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        state.coin?.let { coin ->

            LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(20.dp)) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {

                        Text(
                            text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                            style = MaterialTheme.typography.headlineMedium, modifier = Modifier.weight(8f),
                        )
                        Text(
                            text = if (coin.isActive) "active" else "inactive",
                            color = if (coin.isActive) Color.Green else Color.Red,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .weight(2f)
                        )

                    }

                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = coin.description, style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = "tags", style = MaterialTheme.typography.headlineLarge)
                    FlowRow(
                        mainAxisSpacing = 10.dp,
                        crossAxisSpacing = 10.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        coin.tags.forEach { tag ->
                            CoinTag(tag = tag)

                        }

                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = "Team Member", style = MaterialTheme.typography.headlineLarge)
                    Spacer(modifier = Modifier.height(15.dp))


                }

                items(coin.team) { member ->
                    TeamListItem(
                        teamMember = member, modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                    Divider()

                }

            }
        }

        if (state.error.isNotBlank()) {

            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .align(
                        Alignment.Center
                    )
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

        }

    }
}