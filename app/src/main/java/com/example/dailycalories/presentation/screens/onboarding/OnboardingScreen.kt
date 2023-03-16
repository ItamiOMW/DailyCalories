package com.example.dailycalories.presentation.screens.onboarding

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dailycalories.R
import com.example.dailycalories.presentation.screens.onboarding.pager_screens.*
import com.example.dailycalories.presentation.utils.TabItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel(),
    onNavigateForward: () -> Unit,
) {

    val state = viewModel.userInfoState

    val recommendedDailyIntakeState = viewModel.recommendedDailyIntakeState

    val pagerState = rememberPagerState()

    val scaffoldState = rememberScaffoldState()

    val coroutineScope = rememberCoroutineScope()

    val pagerScreens = listOf(
        TabItem(
            title = stringResource(R.string.title_goal),
            screen = {
                GoalPagerScreen(
                    goalType = state.goalType,
                    onNextClicked = { goalType ->
                        viewModel.onEvent(OnboardingEvent.SaveGoal(goalType))
                    },
                )
            },
        ),
        TabItem(
            title = stringResource(R.string.title_gender),
            screen = {
                GenderPagerScreen(
                    gender = state.gender,
                    onNextClicked = { gender ->
                        viewModel.onEvent(OnboardingEvent.SaveGender(gender))

                    },
                    onPreviousClicked = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                )
            }
        ),
        TabItem(
            title = stringResource(R.string.title_age),
            screen = {
                AgePagerScreen(
                    age = state.age,
                    onNextClicked = { age ->
                        viewModel.onEvent(OnboardingEvent.SaveAge(age))
                    },
                    onPreviousClicked = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                )
            }
        ),
        TabItem(
            title = stringResource(R.string.title_height),
            screen = {
                HeightPagerScreen(
                    height = state.height,
                    onNextClicked = { height ->
                        viewModel.onEvent(OnboardingEvent.SaveHeight(height))
                    },
                    onPreviousClicked = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                )
            }
        ),
        TabItem(
            title = stringResource(R.string.title_weight),
            screen = {
                WeightPagerScreen(
                    weight = state.weight,
                    onNextClicked = { weight ->
                        viewModel.onEvent(OnboardingEvent.SaveWeight(weight))
                    },
                    onPreviousClicked = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                )
            }
        ),
        TabItem(
            title = stringResource(R.string.title_activity_level),
            screen = {
                ActivityLevelPagerScreen(
                    activityLevel = state.activityLevel,
                    onNextClicked = { activityLevel ->
                        viewModel.onEvent(OnboardingEvent.SaveActivityLevel(activityLevel))
                    },
                    onPreviousClicked = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                )
            }
        ),
        TabItem(
            title = stringResource(R.string.title_recom_cal_intake),
            screen = {
                RecommendedDailyCaloriesIntake(
                    fat = recommendedDailyIntakeState.recommendedFat,
                    proteins = recommendedDailyIntakeState.recommendedProteins,
                    carbs = recommendedDailyIntakeState.recommendedCarbs,
                    calories = recommendedDailyIntakeState.recommendedCals,
                    goalType = state.goalType,
                    onSaveClicked = { fat: Float, proteins: Float, carbs: Float, calories: Int ->
                        viewModel.onEvent(
                            OnboardingEvent.SaveDailyCaloriesIntake(
                                fat = fat,
                                proteins = proteins,
                                carbs = carbs,
                                calories = calories
                            )
                        )
                    },
                    onPreviousClicked = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    },
                )
            }
        ),
    )



    BackHandler(enabled = pagerState.currentPage != 0) {
        coroutineScope.launch {
            pagerState.animateScrollToPage(pagerState.currentPage - 1)
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is OnboardingUiEvent.NavigateNext -> {
                    onNavigateForward()
                }
                is OnboardingUiEvent.ShowNextPage -> {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
                is OnboardingUiEvent.ShowPreviousPage -> {
                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                }
                is OnboardingUiEvent.ShowSnackbar -> {
                    //Cant override default value of SnackbarDuration so use this method
                    launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            uiEvent.message,
                            duration = SnackbarDuration.Indefinite
                        )
                        delay(1000)
                        cancel()
                    }
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
    ) {
        Column(
            modifier = Modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Indicators(size = pagerState.pageCount, index = pagerState.currentPage)
            }
            HorizontalPager(
                count = pagerScreens.size,
                state = pagerState,
                userScrollEnabled = false
            ) { pagerIndex ->
                pagerScreens[pagerIndex].screen()
            }
        }
    }

}


@Composable
fun BoxScope.Indicators(size: Int, index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.align(Alignment.Center)
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
        }
    }
}


@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 25.dp else 10.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    Box(
        modifier = Modifier
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if (isSelected) MaterialTheme.colors.onBackground
                else MaterialTheme.colors.onBackground.copy(alpha = 0.5f)
            )
    )
}