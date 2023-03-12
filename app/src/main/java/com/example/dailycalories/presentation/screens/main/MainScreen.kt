package com.example.dailycalories.presentation.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.dailycalories.navigation.Screen
import com.example.dailycalories.navigation.graph.Graph
import com.example.dailycalories.navigation.graph.RootNavGraph
import com.example.dailycalories.navigation.rememberNavigationState
import com.example.dailycalories.presentation.screens.main.components.AppBottomBar


@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
) {

    val navState = rememberNavigationState()

    var bottomBarIsVisible by rememberSaveable { (mutableStateOf(true)) }

    val backStackEntry by navState.navHostController.currentBackStackEntryAsState()

    bottomBarIsVisible = when (backStackEntry?.destination?.route) {
        Screen.HomeScreen.fullRoute -> {
            true
        }
        Screen.ProfileScreen.fullRoute -> {
            true
        }
        Screen.MealsScreen.fullRoute -> {
            true
        }
        else -> {
            false
        }
    }

    Scaffold(
        bottomBar = {
            if (bottomBarIsVisible) {
                AppBottomBar(
                    navItems = listOf(
                        BottomNavItem.Home,
                        BottomNavItem.Meals,
                        BottomNavItem.Profile
                    ),
                    backStackEntry = backStackEntry,
                    onNavItemClick = { navItem ->
                        navState.navigateWithPopUpToStartDestination(navItem.route)
                    }
                )
            }
        }
    ) {
        val startGraphRoute = when (mainViewModel.state) {
            is MainScreenState.OnBoarding -> Graph.ONBOARDING
            is MainScreenState.Main -> Graph.MAIN
            is MainScreenState.Initial -> Graph.MAIN
        }
        Box(modifier = Modifier.padding(it)) {
            RootNavGraph(
                navState = navState,
                startGraphRoute = startGraphRoute,
            )
        }
    }
}
