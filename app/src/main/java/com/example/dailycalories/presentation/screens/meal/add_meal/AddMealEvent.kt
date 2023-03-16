package com.example.dailycalories.presentation.screens.meal.add_meal

import com.example.dailycalories.domain.model.meal.MealFoodProduct

sealed class AddMealEvent {

    data class AddMealProduct(
        val mealProduct: MealFoodProduct,
    ) : AddMealEvent()

    data class ChangeMealProductGrams(
        val mealProduct: MealFoodProduct,
        val weight: Float,
    ) : AddMealEvent()

    data class DeleteMealProduct(val product: MealFoodProduct) : AddMealEvent()

    data class ChangeMealName(val name: String) : AddMealEvent()

    data class ChangeTime(val time: Long) : AddMealEvent()

    object SaveMeal : AddMealEvent()

    data class ShowEditProductWeightDialog(
        val product: MealFoodProduct,
    ) : AddMealEvent()


    object HideEditProductWeightDialog : AddMealEvent()

}