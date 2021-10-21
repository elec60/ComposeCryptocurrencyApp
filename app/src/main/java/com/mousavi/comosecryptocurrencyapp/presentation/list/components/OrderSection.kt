package com.mousavi.comosecryptocurrencyapp.presentation.list.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mousavi.comosecryptocurrencyapp.presentation.list.CoinsViewModel
import com.mousavi.comosecryptocurrencyapp.presentation.util.CoinOrder
import com.mousavi.comosecryptocurrencyapp.presentation.util.OrderType

@Composable
fun OrderSection(
    viewModel: CoinsViewModel,
) {
    val coinOrder = viewModel.userEventState.value.order
    val currentEvent = viewModel.userEventState.value

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Row {
            OrderItem(text = "Name", isSelected = coinOrder is CoinOrder.Name) {
                viewModel.onEvent(currentEvent.copy(order = CoinOrder.Name(coinOrder.orderType)))
            }
            Spacer(modifier = Modifier.width(10.dp))
            OrderItem(text = "Rank>=1", isSelected = coinOrder is CoinOrder.Rank) {
                viewModel.onEvent(currentEvent.copy(order = CoinOrder.Rank(coinOrder.orderType)))
            }
            Spacer(modifier = Modifier.width(10.dp))
            OrderItem(text = "Active", isSelected = coinOrder is CoinOrder.Active) {
                viewModel.onEvent(currentEvent.copy(order = CoinOrder.Active(coinOrder.orderType)))
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            OrderItem(text = "Ascending", isSelected = coinOrder.orderType is OrderType.Ascending) {
                viewModel.onEvent(currentEvent.copy(order = coinOrder.copy(orderType = OrderType.Ascending)))
            }
            Spacer(modifier = Modifier.width(10.dp))
            OrderItem(text = "Descending",
                isSelected = coinOrder.orderType is OrderType.Descending) {
                viewModel.onEvent(currentEvent.copy(order = coinOrder.copy(orderType = OrderType.Descending)))
            }
        }
    }
}

private fun CoinOrder.copy(orderType: OrderType): CoinOrder {
    return when (this) {
        is CoinOrder.Active -> CoinOrder.Active(orderType)
        is CoinOrder.Name -> CoinOrder.Name(orderType)
        is CoinOrder.Rank -> CoinOrder.Rank(orderType)
    }
}
