package com.example.dailycalories.domain.usecase.user_info_validation

import com.example.dailycalories.R
import com.example.dailycalories.domain.model.user.ActivityLevel
import com.example.dailycalories.domain.utils.ValidationResult
import javax.inject.Inject

class ValidateActivityLevel @Inject constructor() {

    operator fun invoke(activityLevel: ActivityLevel): ValidationResult {
        if (activityLevel is ActivityLevel.Unknown) {
            return ValidationResult(
                successful = false,
                errorMessageResId = R.string.error_choose_activity_level
            )
        }
        return ValidationResult(true)
    }

}