package com.example.dailycalories.presentation.screens.meal.search_product

sealed class SearchProductUiEvent {

    data class ShowSnackbar(val message: String) : SearchProductUiEvent()

}
