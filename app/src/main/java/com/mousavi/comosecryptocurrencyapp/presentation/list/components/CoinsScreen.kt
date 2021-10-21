package com.mousavi.comosecryptocurrencyapp.presentation.list.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.mousavi.comosecryptocurrencyapp.presentation.list.CoinsViewModel

@ExperimentalAnimationApi
@Composable
fun CoinsScreen(
    scrollState: LazyListState,
    viewModel: CoinsViewModel,
) {
    val statList = viewModel.state.value
    val loading = viewModel.loading.collectAsState(initial = true)
    val userEvent = viewModel.userEventState.value

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AnimatedVisibility(
            visible = userEvent.isOrderSectionVisible,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            OrderSection(viewModel)
        }

        if (loading.value) {
            Column {
                repeat(30) {
                    ShimmerAnimationView()
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(
                    state = scrollState,
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(statList) { coin ->
                        CoinItem(coin)
                    }
                }
            }
        }
    }

}