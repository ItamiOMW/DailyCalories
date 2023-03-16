package com.example.dailycalories.domain.usecase.meal_validation

import com.example.dailycalories.R
import com.example.dailycalories.domain.model.meal.Meal
import com.example.dailycalories.domain.utils.ValidationResult
import javax.inject.Inject

class ValidateMeal @Inject constructor() {

    operator fun invoke(meal: Meal): ValidationResult {
        if (meal.name.isBlank() && meal.name.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessageResId = R.string.error_name_is_empty
            )
        }
        if (meal.products.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessageResId = R.string.error_add_food_meal
            )
        }
        return ValidationResult(true)
    }

}