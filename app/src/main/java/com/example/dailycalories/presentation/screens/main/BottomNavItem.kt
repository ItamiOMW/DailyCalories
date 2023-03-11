package com.example.dailycalories.presentation.screens.main

import com.example.dailycalories.R
import com.example.dailycalories.navigation.graph.Graph

sealed class BottomNavItem(
    val titleResId: Int,
    val iconResId: Int,
    val route: String
) {


    object Home: BottomNavItem(
        titleResId = R.string.nav_item_title_home,
        iconResId = R.drawable.menu,
        route = Graph.HOME
    )

    object Meals: BottomNavItem(
        titleResId = R.string.nav_item_title_meals,
        iconResId = R.drawable.eating,
        route = Graph.MEALS
    )

    object Profile: BottomNavItem(
        titleResId = R.string.nav_item_title_profile,
        iconResId = R.drawable.profile,
        route = Graph.PROFILE
    )


}
