package com.mousavi.comosecryptocurrencyapp.presentation.list.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mousavi.comosecryptocurrencyapp.domain.model.Coin

@Composable
fun CoinItem(
    coin: Coin,
) {
    Card(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 16.dp)
            .fillMaxWidth()
            .shadow(14.dp, shape = RoundedCornerShape(18.dp))
            .border(width = 1.dp, color = Color.Blue, shape = RoundedCornerShape(18.dp)),
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text(text = coin.name)
                Text(text = "Rank: ${coin.rank}", color = Color.Gray.copy(alpha = 0.8f))
            }
            Text(
                text = if (coin.isActive) "Active" else "Inactive",
                color = if (coin.isActive) Color.Green else Color.Red
            )
        }
    }
}