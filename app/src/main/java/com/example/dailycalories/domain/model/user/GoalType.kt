package com.example.dailycalories.domain.model.user

import com.example.dailycalories.R

sealed class GoalType(
    val type: String,
    val nameResId: Int,
) {

    object LoseWeight : GoalType(LOSE_WEIGHT, R.string.title_lose_weight)

    object KeepWeight : GoalType(KEEP_WEIGHT, R.string.title_keep_weight)

    object GainWeight : GoalType(GAIN_WEIGHT, R.string.title_gain_weight)

    object Unknown : GoalType(UNKNOWN, R.string.title_unknown)


    companion object {

        private const val LOSE_WEIGHT = "lose_weight"
        private const val KEEP_WEIGHT = "keep_weight"
        private const val GAIN_WEIGHT = "gain_weight"
        private const val UNKNOWN = "unknown"

        fun fromString(string: String): GoalType {
            return when (string) {
                LoseWeight.type -> LoseWeight
                KeepWeight.type -> KeepWeight
                GainWeight.type -> GainWeight
                Unknown.type -> Unknown
                else -> throw Exception("Unknown GoalType: $string")
            }
        }

    }

}
