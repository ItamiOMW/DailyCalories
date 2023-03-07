package com.example.dailycalories.navigation

sealed class Screen(protected val route: String, vararg params: String) {

    val fullRoute: String = if (params.isEmpty()) route else {
        val builder = StringBuilder(route)
        params.forEach { builder.append("/{${it}}") }
        builder.toString()
    }


    object HomeScreen : Screen(route = HOME_SCREEN_ROUTE)

    object MealsScreen : Screen(route = MEALS_SCREEN_ROUTE)

    object ProfileScreen : Screen(route = PROFILE_SCREEN_ROUTE)

    object SearchFoodScreen : Screen(route = SEARCH_FOOD_SCREEN_ROUTE)


    companion object {

        //Screen routes
        private const val HOME_SCREEN_ROUTE = "home"

        private const val MEALS_SCREEN_ROUTE = "meals"

        private const val TOOL_SCREEN_ROUTE = "instruments"

        private const val PROFILE_SCREEN_ROUTE = "instruments"

        private const val SEARCH_FOOD_SCREEN_ROUTE = "search_food"

        //Arguments

        //Graph routes
        const val PROFILE_GRAPH_ROUTE = "profile_graph"

        const val MEALS_GRAPH_ROUTE = "meals_graph"

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
