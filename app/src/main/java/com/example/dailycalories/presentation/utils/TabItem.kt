package com.example.dailycalories.presentation.utils

import androidx.compose.runtime.Composable

data class TabItem(
    val title: String,
    val screen: @Composable () -> Unit,
)
