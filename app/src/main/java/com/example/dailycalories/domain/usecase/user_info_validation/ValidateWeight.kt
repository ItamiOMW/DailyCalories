package com.example.dailycalories.domain.usecase.user_info_validation

import com.example.dailycalories.R
import com.example.dailycalories.domain.utils.ValidationResult
import javax.inject.Inject

class ValidateWeight @Inject constructor() {

    operator fun invoke(weight: Float): ValidationResult {

        if (weight in (20f..499f)) {
            return ValidationResult(true)
        }
        return ValidationResult(
            successful = false,
            errorMessageResId = R.string.error_specify_actual_weight
        )
    }

}