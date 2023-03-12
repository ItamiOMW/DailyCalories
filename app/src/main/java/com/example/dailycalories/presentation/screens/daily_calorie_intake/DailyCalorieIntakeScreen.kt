package com.example.dailycalories.presentation.screens.daily_calorie_intake

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun DailyCalorieIntakeScreen(
    viewModel: DailyCalorieIntakeViewModel = hiltViewModel(),
    onNavigateBack: (() -> Unit)? = null,
    onNavigateForward: (() -> Unit)? = null,
) {

    val state = viewModel.state

    if (state.isInitializing) {
        Initializing()
    } else {
        ScreenContent(viewModel = viewModel)
    }


}


@Composable
private fun Initializing() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ScreenContent(
    viewModel: DailyCalorieIntakeViewModel,
) {
    Column() {

    }
}