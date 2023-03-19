package com.example.dailycalories.presentation.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailycalories.data.UserSettingsManager
import com.example.dailycalories.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val userSettingsManager: UserSettingsManager
) : ViewModel() {


    var state by mutableStateOf<MainScreenState>(MainScreenState.Initial)
        private set

    val isDarkTheme = userSettingsManager.isDarkTheme

    init {
        viewModelScope.launch {
            userRepository.getShouldShowOnBoarding().collect { shouldShow ->
                state = if (shouldShow) {
                    MainScreenState.OnBoarding
                } else {
                    MainScreenState.Main
                }
                return@collect
            }
        }
    }


}