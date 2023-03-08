package com.example.dailycalories.domain.model.user


sealed class ActivityLevel(val name: String) {

    object Low : ActivityLevel(LOW)

    object Medium : ActivityLevel(MEDIUM)

    object High : ActivityLevel(HIGH)

    object SuperHigh : ActivityLevel(SUPER_HIGH)

    companion object {

        private const val LOW = "LOW"
        private const val MEDIUM = "MEDIUM"
        private const val HIGH = "HIGH"
        private const val SUPER_HIGH = "SUPERHIGH"

        private val smth = Low.name

        fun fromString(string: String): ActivityLevel {
            return when (string) {
                Low.name -> Low
                Medium.name -> Medium
                High.name -> High
                SuperHigh.name -> SuperHigh
                else -> throw Exception("Unknown Activity level: $string")
            }
        }

    }

}

