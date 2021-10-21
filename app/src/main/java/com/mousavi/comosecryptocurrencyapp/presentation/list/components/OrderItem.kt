package com.mousavi.comosecryptocurrencyapp.presentation.list.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun OrderItem(
    text: String,
    isSelected: Boolean,
    onSelected: () -> Unit,
) {
    Row {
        RadioButton(selected = isSelected, onClick = onSelected)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text, color = Color.Black)
    }
}