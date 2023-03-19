package com.example.dailycalories.presentation.screens.profile.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailycalories.data.UserSettingsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userSettingsManager: UserSettingsManager,
) : ViewModel() {


    val isDarkTheme = userSettingsManager.isDarkTheme

    fun changeDarkTheme(darkTheme: Boolean) {
        viewModelScope.launch {
            userSettingsManager.setIsDarkTheme(darkTheme)
        }
    }

}