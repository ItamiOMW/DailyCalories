package com.example.dailycalories.presentation.screens.profile.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dailycalories.R


@Composable
fun ProfileScreen(
    onNavigateToOnboarding: () -> Unit,
    onNavigateToCalorieCalculator: () -> Unit,
    onNavigateToCalorieIntake: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel(),
) {

    val isDarkTheme = viewModel.isDarkTheme.collectAsState(initial = false)


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.title_profile),
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
            )
            IconButton(
                modifier = Modifier.align(Alignment.CenterEnd),
                onClick = {
                    viewModel.changeDarkTheme(!isDarkTheme.value)
                }
            ) {
                Icon(
                    imageVector = if (isDarkTheme.value) Icons.Default.DarkMode
                    else Icons.Default.LightMode,
                    contentDescription = stringResource(R.string.desc_theme_icon)
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))
        UnderlinedTextItem(
            text = stringResource(R.string.title_calorie_calculator),
            icon = Icons.Outlined.KeyboardArrowRight,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onNavigateToCalorieCalculator()
                }
                .padding(start = 10.dp, end = 10.dp)
                .background(MaterialTheme.colors.background),
        )
        UnderlinedTextItem(
            text = stringResource(R.string.title_daily_calorie_intake),
            icon = Icons.Outlined.KeyboardArrowRight,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onNavigateToCalorieIntake()
                }
                .padding(start = 10.dp, end = 10.dp)
                .background(MaterialTheme.colors.background),
        )
        UnderlinedTextItem(
            text = stringResource(R.string.title_change_goal),
            icon = Icons.Outlined.KeyboardArrowRight,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onNavigateToOnboarding()
                }
                .padding(start = 10.dp, end = 10.dp)
                .background(MaterialTheme.colors.background),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
                .background(MaterialTheme.colors.background),
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.title_dark_theme),
                    style = MaterialTheme.typography.h5
                )
                Switch(
                    checked = isDarkTheme.value,
                    onCheckedChange = { checked ->
                        viewModel.changeDarkTheme(checked)
                    },
                    colors = SwitchDefaults.colors()
                )
            }
            Divider()
        }
    }


}


@Composable
private fun UnderlinedTextItem(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = text, style = MaterialTheme.typography.h5)
            Icon(imageVector = icon, contentDescription = text)
        }
        Divider()
    }
}