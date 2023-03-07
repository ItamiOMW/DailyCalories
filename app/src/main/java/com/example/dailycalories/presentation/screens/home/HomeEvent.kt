package com.example.dailycalories.presentation.screens.home

sealed class HomeEvent {

    data class ChangeDate(val date: String): HomeEvent()

}
