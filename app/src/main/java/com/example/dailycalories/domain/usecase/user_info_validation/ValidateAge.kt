package com.example.dailycalories.domain.usecase.user_info_validation

import com.example.dailycalories.R
import com.example.dailycalories.domain.utils.ValidationResult
import javax.inject.Inject

class ValidateAge @Inject constructor() {

    operator fun invoke(age: Int): ValidationResult {
        if (age in (10..110)) {
            return ValidationResult(
                successful = true,
            )
        }
        return ValidationResult(
            successful = false,
            errorMessageResId = R.string.error_specify_actual_age
        )
    }
}