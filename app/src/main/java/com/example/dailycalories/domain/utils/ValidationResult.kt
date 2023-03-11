package com.example.dailycalories.domain.utils

data class ValidationResult(
    val successful: Boolean,
    val errorMessageResId: Int? = null
)
