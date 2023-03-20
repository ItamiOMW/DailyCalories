package com.example.dailycalories.domain.usecase.calorie_calculator_validation

import com.example.dailycalories.R
import com.example.dailycalories.domain.model.user.ActivityLevel
import com.example.dailycalories.domain.model.user.Gender
import com.example.dailycalories.domain.model.user.GoalType
import com.example.dailycalories.domain.utils.ValidationResult
import javax.inject.Inject

class ValidateInfoForCalculation @Inject constructor() {

    operator fun invoke(
        proteinsPercentage: Int,
        fatPercentage: Int,
        carbsPercentage: Int,
        activityLevel: ActivityLevel,
        gender: Gender,
        goalType: GoalType,
        weight: Float,
        height: Int,
        age: Int,
    ): ValidationResult {
        val sum = proteinsPercentage + fatPercentage + carbsPercentage
        if (sum != 100) {
            return ValidationResult(
                successful = false,
                errorMessageResId = R.string.error_pfc_ration
            )
        }
        if (activityLevel is ActivityLevel.Unknown) {
            return ValidationResult(
                successful = false,
                errorMessageResId = R.string.error_choose_activity_level
            )
        }
        if (gender is Gender.Unknown) {
            return ValidationResult(
                successful = false,
                errorMessageResId = R.string.error_choose_gender
            )
        }
        if (goalType is GoalType.Unknown) {
            return ValidationResult(
                successful = false,
                errorMessageResId = R.string.error_choose_goal
            )
        }
        if (weight <= 0f) {
            return ValidationResult(
                successful = false,
                errorMessageResId = R.string.error_weight
            )
        }
        if (height <= 0f) {
            return ValidationResult(
                successful = false,
                errorMessageResId = R.string.error_height
            )
        }
        if (age <= 0) {
            return ValidationResult(
                successful = false,
                errorMessageResId = R.string.error_age
            )
        }
        return ValidationResult(true)
    }

}