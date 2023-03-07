package com.example.dailycalories.presentation.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import com.example.dailycalories.presentation.theme.ui.DailyCaloriesTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyCaloriesTheme() {

                val systemUiController = rememberSystemUiController().apply {
                    setSystemBarsColor(MaterialTheme.colors.primaryVariant)
                }

                Box(Modifier.fillMaxSize()) {
                    MainScreen(mainViewModel = viewModel)
                }

            }
        }
    }
}
