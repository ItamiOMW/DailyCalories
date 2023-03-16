package com.example.dailycalories.presentation.screens.meal.search_product

import com.example.dailycalories.domain.model.food_product.FoodProductInfo

data class SearchProductState(
    val query: String = "",
    val products: List<FoodProductInfo> = emptyList(),
    val showAddProductDialog: Boolean = false,
    val selectedProduct: FoodProductInfo? = null,
    val isLoading: Boolean = false
)
