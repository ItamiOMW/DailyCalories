package com.example.dailycalories.domain.model.user

sealed class Gender(val name: String) {

    object Male : Gender(GENDER_MALE)

    object Female : Gender(GENDER_FEMALE)

    companion object {

        private const val GENDER_MALE = "male"
        private const val GENDER_FEMALE = "female"

        fun fromString(string: String): Gender {
            return when(string) {
                Male.name -> Male
                Female.name -> Female
                else -> throw Exception("Unknown Gender: $string")
            }
        }

    }

}
