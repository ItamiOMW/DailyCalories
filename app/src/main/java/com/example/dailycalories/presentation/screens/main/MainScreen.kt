package com.example.dailycalories.presentation.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.dailycalories.navigation.AppNavGraph
import com.example.dailycalories.navigation.rememberNavigationState
import com.example.dailycalories.presentation.screens.home.HomeScreen
import com.example.dailycalories.presentation.screens.main.components.AppBottomBar
import com.example.dailycalories.presentation.screens.meals.MealsScreen
import com.example.dailycalories.presentation.screens.profile.ProfileScreen


@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
) {

    val navState = rememberNavigationState()

    Scaffold(
        bottomBar = {
            AppBottomBar(
                navItems = listOf(
                    BottomNavItem.Home,
                    BottomNavItem.Meals,
                    BottomNavItem.Profile
                ),
                backStackEntry = navState.navHostController.currentBackStackEntryAsState().value,
                onNavItemClick = { navItem ->
                    navState.navigateToWithPopUpToStartDestination(navItem.screen.fullRoute)
                }
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            AppNavGraph(
                navHostController = navState.navHostController,
                HomeScreenContent = { HomeScreen() },
                MealsScreenContent = { MealsScreen() },
                ProfileScreenContent = { ProfileScreen() }
            )
        }
    }

}