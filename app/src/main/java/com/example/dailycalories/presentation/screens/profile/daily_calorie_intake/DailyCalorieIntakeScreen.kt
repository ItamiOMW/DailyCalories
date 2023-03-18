package com.example.dailycalories.presentation.screens.profile.daily_calorie_intake

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DailyCalorieIntakeScreen(
    viewModel: DailyCalorieIntakeViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
) {


    ModalBottomSheetLayout(
        sheetContent = {

        }
    ) {

    }

}