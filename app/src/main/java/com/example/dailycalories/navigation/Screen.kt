package com.example.dailycalories.navigation

sealed class Screen(protected val route: String, vararg params: String) {

    val fullRoute: String = if (params.isEmpty()) route else {
        val builder = StringBuilder(route)
        params.forEach { builder.append("/{${it}}") }
        builder.toString()
    }


    
    object OnboardingScreen: Screen(route = ONBOARDING_SCREEN_ROUTE)
    
    
    object RecommendedNutritionScreen: Screen(route = RECOMMENDED_NUTRITION_SCREEN_ROUTE)
            
            
    object HomeScreen : Screen(route = HOME_SCREEN_ROUTE)

    
    object MealsScreen : Screen(route = MEALS_SCREEN_ROUTE)

    
    object ProfileScreen : Screen(route = PROFILE_SCREEN_ROUTE)

    
    object SearchFoodScreen : Screen(route = SEARCH_FOOD_SCREEN_ROUTE)


    companion object {

        //Screen routes
        private const val ONBOARDING_SCREEN_ROUTE = "onboarding"

        private const val RECOMMENDED_NUTRITION_SCREEN_ROUTE = "recommended_nutrition"

        private const val HOME_SCREEN_ROUTE = "home"

        private const val MEALS_SCREEN_ROUTE = "meals"

        private const val TOOL_SCREEN_ROUTE = "instruments"

        private const val PROFILE_SCREEN_ROUTE = "instruments"
        
        private const val SEARCH_FOOD_SCREEN_ROUTE = "search_food"

        //Arguments


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
