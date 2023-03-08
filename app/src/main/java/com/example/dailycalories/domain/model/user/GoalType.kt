package com.example.dailycalories.domain.model.user

sealed class GoalType(val name: String) {

    object LoseWeight: GoalType(LOSE_WEIGHT)

    object KeepWeight: GoalType(KEEP_WEIGHT)

    object GainWeight: GoalType(GAIN_WEIGHT)


    companion object {

        private const val LOSE_WEIGHT = "lose_weight"
        private const val KEEP_WEIGHT = "keep_weight"
        private const val GAIN_WEIGHT = "gain_weight"

        fun fromString(string: String): GoalType {
            return when(string) {
                LoseWeight.name -> LoseWeight
                KeepWeight.name -> KeepWeight
                GainWeight.name -> GainWeight
                else -> throw Exception("Unknown GoalType: $string")
            }
        }

    }

}
