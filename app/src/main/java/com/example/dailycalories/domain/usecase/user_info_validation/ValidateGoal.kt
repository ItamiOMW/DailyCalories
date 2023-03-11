package com.example.dailycalories.domain.usecase.user_info_validation

import com.example.dailycalories.R
import com.example.dailycalories.domain.model.user.GoalType
import com.example.dailycalories.domain.utils.ValidationResult
import javax.inject.Inject

class ValidateGoal @Inject constructor() {

    operator fun invoke(goalType: GoalType): ValidationResult {
        if (goalType is GoalType.Unknown) {
            return ValidationResult(
                successful = false,
                errorMessageResId = R.string.error_choose_goal
            )
        }
        return ValidationResult(true)
    }

}