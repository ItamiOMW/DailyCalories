package com.example.dailycalories.presentation.screens.meal.meal_detail

import com.example.dailycalories.domain.model.meal.MealFoodProduct

sealed class MealDetailEvent {

    data class AddMealProduct(
        val mealProduct: MealFoodProduct,
    ) : MealDetailEvent()

    data class ChangeMealProductGrams(
        val weight: Float,
    ) : MealDetailEvent()

    data class ShowEditProductWeightDialog(
        val product: MealFoodProduct,
    ) : MealDetailEvent()


    object HideEditProductWeightDialog : MealDetailEvent()


}