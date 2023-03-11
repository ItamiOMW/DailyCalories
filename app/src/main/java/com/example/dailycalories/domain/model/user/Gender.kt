package com.example.dailycalories.domain.model.user

import com.example.dailycalories.R

sealed class Gender(
    val type: String,
    val nameResId: Int,
) {

    object Male : Gender(GENDER_MALE, R.string.title_male)

    object Female : Gender(GENDER_FEMALE, R.string.title_female)

    object Unknown : Gender(GENDER_UNKNOWN, R.string.title_unknown_gender)

    companion object {

        private const val GENDER_MALE = "male"
        private const val GENDER_FEMALE = "female"
        private const val GENDER_UNKNOWN = "unknown"

        fun fromString(string: String): Gender {
            return when(string) {
                Male.type -> Male
                Female.type -> Female
                Unknown.type -> Unknown
                else -> throw Exception("Unknown Gender: $string")
            }
        }

    }

}
