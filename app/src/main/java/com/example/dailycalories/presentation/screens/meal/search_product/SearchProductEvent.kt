package com.example.dailycalories.presentation.screens.meal.search_product

import com.example.dailycalories.domain.model.food_product.FoodProductInfo

sealed class SearchProductEvent {


    data class ChangeQuery(val query: String) : SearchProductEvent()


    data class ShowAddProductDialog(
        val product: FoodProductInfo,
    ) : SearchProductEvent()


    object HideAddProductDialog : SearchProductEvent()

}
