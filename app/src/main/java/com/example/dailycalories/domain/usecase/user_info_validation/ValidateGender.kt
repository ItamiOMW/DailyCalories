package com.example.dailycalories.domain.usecase.user_info_validation

import com.example.dailycalories.R
import com.example.dailycalories.domain.model.user.Gender
import com.example.dailycalories.domain.utils.ValidationResult
import javax.inject.Inject

class ValidateGender @Inject constructor() {

    operator fun invoke(gender: Gender): ValidationResult {
        if (gender is Gender.Unknown) {
            return ValidationResult(
                successful = false,
                R.string.error_choose_gender
            )
        }
        return ValidationResult(true)
    }

}