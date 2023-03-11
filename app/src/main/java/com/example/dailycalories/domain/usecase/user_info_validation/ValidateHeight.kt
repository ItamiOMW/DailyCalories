package com.example.dailycalories.domain.usecase.user_info_validation

import com.example.dailycalories.R
import com.example.dailycalories.domain.utils.ValidationResult
import javax.inject.Inject

class ValidateHeight @Inject constructor() {

    operator fun invoke(height: Int): ValidationResult {
        if (height in (101..250)) {
            return ValidationResult(true)
        }
        return ValidationResult(
            successful = false,
            errorMessageResId = R.string.error_specify_actual_height
        )
    }

}