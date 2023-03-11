package com.example.dailycalories.domain.model.user

import com.example.dailycalories.R


sealed class ActivityLevel(
    val type: String,
    val nameResId: Int,
) {

    object Low : ActivityLevel(LOW, R.string.title_activity_low)

    object Medium : ActivityLevel(MEDIUM, R.string.title_activity_medium)

    object High : ActivityLevel(HIGH, R.string.title_activity_high)

    object SuperHigh : ActivityLevel(SUPER_HIGH, R.string.title_activity_superhigh)

    object Unknown : ActivityLevel(UNKNOWN, R.string.title_activity_unknown)

    companion object {

        private const val LOW = "LOW"
        private const val MEDIUM = "MEDIUM"
        private const val HIGH = "HIGH"
        private const val SUPER_HIGH = "SUPERHIGH"
        private const val UNKNOWN = "UNKNOWN"

        fun fromString(string: String): ActivityLevel {
            return when (string) {
                Low.type -> Low
                Medium.type -> Medium
                High.type -> High
                SuperHigh.type -> SuperHigh
                Unknown.type -> Unknown
                else -> throw Exception("Unknown Activity level: $string")
            }
        }

    }

}

