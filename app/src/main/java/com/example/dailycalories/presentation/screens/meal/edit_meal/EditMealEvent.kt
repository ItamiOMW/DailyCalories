package com.example.dailycalories.presentation.screens.meal.edit_meal

import com.example.dailycalories.domain.model.meal.MealFoodProduct

sealed class EditMealEvent {

    data class AddMealProduct(
        val mealProduct: MealFoodProduct,
    ) : EditMealEvent()

    data class ChangeMealProductGrams(
        val editedMealProduct: MealFoodProduct,
        val weight: Float,
    ) : EditMealEvent()

    data class DeleteMealProduct(val product: MealFoodProduct) : EditMealEvent()

    data class ChangeMealName(val name: String) : EditMealEvent()

    data class ChangeTime(val time: Long) : EditMealEvent()

    object SaveMeal : EditMealEvent()

    data class ShowEditProductWeightDialog(
        val product: MealFoodProduct,
    ) : EditMealEvent()


    object HideEditProductWeightDialog : EditMealEvent()


    data class ShowTimePickerDialog(
        val show: Boolean,
    ) : EditMealEvent()

}