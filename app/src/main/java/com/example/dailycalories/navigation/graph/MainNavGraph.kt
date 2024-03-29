package com.example.dailycalories.navigation.graph

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.dailycalories.navigation.NavigationState
import com.example.dailycalories.navigation.Screen
import com.example.dailycalories.navigation.ext.navigateForResult
import com.example.dailycalories.navigation.ext.popBackStackWithResult
import com.example.dailycalories.presentation.screens.home.HomeScreen
import com.example.dailycalories.presentation.screens.meal.add_meal.AddMealScreen
import com.example.dailycalories.presentation.screens.meal.edit_meal.EditMealScreen
import com.example.dailycalories.presentation.screens.meal.meal_detail.MealDetailScreen
import com.example.dailycalories.presentation.screens.meal.meals.MealsScreen
import com.example.dailycalories.presentation.screens.meal.search_product.SearchProductScreen
import com.example.dailycalories.presentation.screens.profile.daily_calorie_intake.DailyCalorieIntakeScreen
import com.example.dailycalories.presentation.screens.profile.profile.ProfileScreen
import com.example.dailycalories.presentation.screens.profile.calorie_calculator.CalorieCalculatorScreen
import com.example.dailycalories.utils.UNKNOWN_ID
import com.example.dailycalories.utils.getCurrentDateString
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.mainNavGraph(
    navState: NavigationState,
) {
    navigation(
        route = Graph.MAIN,
        startDestination = Graph.HOME
    ) {
        homeScreenNavGraph(navState = navState)
        mealsScreenNavGraph(navState = navState)
        profileScreenNavGraph(navState = navState)
    }
}


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeScreenNavGraph(
    navState: NavigationState,
) {
    navigation(
        startDestination = Screen.HomeScreen.fullRoute,
        route = Graph.HOME
    ) {
        composable(
            route = Screen.HomeScreen.fullRoute
        ) {
            HomeScreen()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.mealsScreenNavGraph(
    navState: NavigationState,
) {
    navigation(
        startDestination = Screen.MealsScreen.fullRoute,
        route = Graph.MEALS
    ) {
        composable(
            route = Screen.MealsScreen.fullRoute
        ) {
            MealsScreen(
                onNavigateToAddMeal = { date ->
                    navState.navigateTo(route = Screen.AddMealScreen.getRouteWithArgs(date))
                },
                onNavigateToEditMeal = { id ->
                    navState.navigateTo(route = Screen.EditMealScreen.getRouteWithArgs(id))
                },
                onNavigateToMealDetail = { id ->
                    navState.navigateTo(route = Screen.MealDetailScreen.getRouteWithArgs(id))
                }
            )
        }
        composable(
            route = Screen.AddMealScreen.fullRoute,
            arguments = listOf(
                navArgument(Screen.DATE_ARG) {
                    type = NavType.StringType
                    defaultValue = getCurrentDateString()
                }
            ),
            enterTransition = {
                slideInVertically(initialOffsetY = { it }, animationSpec = tween(500))
                    .plus(fadeIn(tween(500)))
            },
            exitTransition = {
                slideOutVertically(targetOffsetY = { -it }, animationSpec = tween(500))
                    .plus(fadeOut(tween(500)))
            },
            popEnterTransition = {
                slideInVertically(initialOffsetY = { -it }, animationSpec = tween(500))
                    .plus(fadeIn(tween(500)))
            },
            popExitTransition = {
                slideOutVertically(targetOffsetY = { it }, animationSpec = tween(500))
                    .plus(fadeOut(tween(500)))
            }
        ) {
            AddMealScreen(
                onNavigateBack = {
                    navState.navigateBack()
                },
                onNavigateToSearchFood = { callback ->
                    navState.navHostController.navigateForResult(
                        Screen.SearchProductScreen.fullRoute,
                        navResultCallback = callback
                    )
                }
            )
        }
        composable(
            route = Screen.SearchProductScreen.fullRoute,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it }, animationSpec = tween(500)
                ).plus(fadeIn(tween(500)))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it }, animationSpec = tween(500)
                ).plus(fadeOut(tween(500)))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it }, animationSpec = tween(500)
                ).plus(fadeIn(tween(500)))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it }, animationSpec = tween(500)
                ).plus(fadeOut(tween(500)))
            }
        ) {
            SearchProductScreen(
                onConfirm = { mealFoodProduct ->
                    navState.navHostController.popBackStackWithResult(mealFoodProduct)
                },
                onNavigateBack = {
                    navState.navigateBack()
                }
            )
        }
        composable(
            route = Screen.EditMealScreen.fullRoute,
            arguments = listOf(
                navArgument(Screen.MEAL_ID_ARG) {
                    type = NavType.LongType
                    defaultValue = UNKNOWN_ID
                }
            ),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it }, animationSpec = tween(500)
                ).plus(fadeIn(tween(500)))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it }, animationSpec = tween(500)
                ).plus(fadeOut(tween(500)))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it }, animationSpec = tween(500)
                ).plus(fadeIn(tween(500)))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it }, animationSpec = tween(500)
                ).plus(fadeOut(tween(500)))
            }
        ) {
            EditMealScreen(
                onNavigateBack = {
                    navState.navigateBack()
                },
                onNavigateToSearchFood = { callback ->
                    navState.navHostController.navigateForResult(
                        Screen.SearchProductScreen.fullRoute,
                        navResultCallback = callback
                    )
                }
            )
        }
        composable(
            route = Screen.MealDetailScreen.fullRoute,
            arguments = listOf(
                navArgument(Screen.MEAL_ID_ARG) {
                    type = NavType.LongType
                    defaultValue = UNKNOWN_ID
                }
            ),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it }, animationSpec = tween(500)
                ).plus(fadeIn(tween(500)))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it }, animationSpec = tween(500)
                ).plus(fadeOut(tween(500)))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it }, animationSpec = tween(500)
                ).plus(fadeIn(tween(500)))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it }, animationSpec = tween(500)
                ).plus(fadeOut(tween(500)))
            }
        ) {
            MealDetailScreen(
                onNavigateBack = {
                    navState.navigateBack()
                },
                onNavigateToSearchFood = { callback ->
                    navState.navHostController.navigateForResult(
                        Screen.SearchProductScreen.fullRoute,
                        navResultCallback = callback
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.profileScreenNavGraph(
    navState: NavigationState,
) {
    navigation(
        startDestination = Screen.ProfileScreen.fullRoute,
        route = Graph.PROFILE
    ) {
        composable(
            route = Screen.ProfileScreen.fullRoute
        ) {
            ProfileScreen(
                onNavigateToOnboarding = {
                    navState.navigateTo(Graph.ONBOARDING)
                },
                onNavigateToCalorieIntake = {
                    navState.navigateTo(Screen.DailyCalorieIntakeScreen.fullRoute)
                },
                onNavigateToCalorieCalculator = {
                    navState.navigateTo(Screen.CalorieCalculatorScreen.fullRoute)
                }
            )
        }
        composable(
            route = Screen.CalorieCalculatorScreen.fullRoute,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it }, animationSpec = tween(500)
                ).plus(fadeIn(tween(500)))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it }, animationSpec = tween(500)
                ).plus(fadeOut(tween(500)))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it }, animationSpec = tween(500)
                ).plus(fadeIn(tween(500)))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it }, animationSpec = tween(500)
                ).plus(fadeOut(tween(500)))
            }
        ) {
            CalorieCalculatorScreen(
                onNavigateBack = {
                    navState.navigateBack()
                }
            )
        }
        composable(
            route = Screen.DailyCalorieIntakeScreen.fullRoute,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it }, animationSpec = tween(500)
                ).plus(fadeIn(tween(500)))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it }, animationSpec = tween(500)
                ).plus(fadeOut(tween(500)))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it }, animationSpec = tween(500)
                ).plus(fadeIn(tween(500)))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it }, animationSpec = tween(500)
                ).plus(fadeOut(tween(500)))
            }
        ) {
            DailyCalorieIntakeScreen(
                onNavigateBack = {
                    navState.navigateBack()
                }
            )
        }
    }
}