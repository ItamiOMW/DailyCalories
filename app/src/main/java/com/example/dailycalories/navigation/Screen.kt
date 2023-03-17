package com.example.dailycalories.navigation

sealed class Screen(protected val route: String, vararg params: String) {

    val fullRoute: String = if (params.isEmpty()) route else {
        val builder = StringBuilder(route)
        params.forEach { builder.append("/{${it}}") }
        builder.toString()
    }


    object OnboardingScreen : Screen(route = ONBOARDING_SCREEN_ROUTE)


    object DailyCalorieIntakeScreen : Screen(route = DAILY_CALORIE_INTAKE_SCREEN_ROUTE)


    object HomeScreen : Screen(route = HOME_SCREEN_ROUTE)


    object MealsScreen : Screen(route = MEALS_SCREEN_ROUTE)


    object ProfileScreen : Screen(route = PROFILE_SCREEN_ROUTE)


    object SearchProductScreen : Screen(route = SEARCH_PRODUCT_SCREEN_ROUTE)

    object AddMealScreen : Screen(route = ADD_MEAL_SCREEN_ROUTE, DATE_ARG) {

        fun getRouteWithArgs(date: String): String = route.appendParams(
            DATE_ARG to date
        )

    }


    object EditMealScreen : Screen(route = EDIT_MEAL_SCREEN_ROUTE, MEAL_ID_ARG) {

        fun getRouteWithArgs(mealId: Long): String = route.appendParams(
            MEAL_ID_ARG to mealId
        )

    }

    object MealDetailScreen : Screen(route = MEAL_DETAIL_SCREEN_ROUTE, MEAL_ID_ARG) {

        fun getRouteWithArgs(mealId: Long): String = route.appendParams(
            MEAL_ID_ARG to mealId
        )

    }


    companion object {

        //Screen routes
        private const val ONBOARDING_SCREEN_ROUTE = "onboarding"
        private const val DAILY_CALORIE_INTAKE_SCREEN_ROUTE = "set_nutrition"
        private const val HOME_SCREEN_ROUTE = "home"
        private const val MEALS_SCREEN_ROUTE = "meals"
        private const val TOOL_SCREEN_ROUTE = "tools"
        private const val PROFILE_SCREEN_ROUTE = "instruments"
        private const val SEARCH_PRODUCT_SCREEN_ROUTE = "search_product"
        private const val EDIT_MEAL_SCREEN_ROUTE = "edit_meal"
        private const val ADD_MEAL_SCREEN_ROUTE = "add_meal"
        private const val MEAL_DETAIL_SCREEN_ROUTE = "meal_detail"

        //Arguments
        const val MEAL_ID_ARG = "meal_id_arg"
        const val DATE_ARG = "date_arg"

        //Result Key
        const val MEAL_FOOD_PRODUCT_RESULT_KEY = "meal_food_product_result"

    }


    internal fun String.appendParams(vararg params: Pair<String, Any?>): String {
        val builder = StringBuilder(this)

        params.forEach {
            it.second?.toString()?.let { arg ->
                builder.append("/$arg")
            }
        }

        return builder.toString()
    }
}
