package com.example.dailycalories.domain.model.user


data class UserInfo(
    val gender: Gender,
    val goalType: GoalType,
    val activityLevel: ActivityLevel,
    val age: Int,
    val height: Int,
    val weight: Float,
    val dailyCarbs: Float,
    val dailyProteins: Float,
    val dailyFat: Float,
    val dailyCals: Int
) {

    companion object {

        val DEFAULT = UserInfo(
            gender = Gender.Unknown,
            goalType = GoalType.Unknown,
            activityLevel = ActivityLevel.Unknown,
            age = 20,
            height = 170,
            weight = 70f,
            dailyCarbs = 0f,
            dailyProteins = 0f,
            dailyFat = 0f,
            dailyCals = 0
        )

    }

}
