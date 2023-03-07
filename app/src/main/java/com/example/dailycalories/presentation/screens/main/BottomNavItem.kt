package com.example.dailycalories.presentation.screens.main

import com.example.dailycalories.R
import com.example.dailycalories.navigation.Screen

sealed class BottomNavItem(
    val titleResId: Int,
    val iconResId: Int,
    val screen: Screen
) {


    object Home: BottomNavItem(
        titleResId = R.string.nav_item_title_home,
        iconResId = R.drawable.menu,
        screen = Screen.HomeScreen
    )

    object Meals: BottomNavItem(
        titleResId = R.string.nav_item_title_meals,
        iconResId = R.drawable.eating,
        screen = Screen.MealsScreen
    )

    object Profile: BottomNavItem(
        titleResId = R.string.nav_item_title_profile,
        iconResId = R.drawable.profile,
        screen = Screen.ProfileScreen
    )


}
