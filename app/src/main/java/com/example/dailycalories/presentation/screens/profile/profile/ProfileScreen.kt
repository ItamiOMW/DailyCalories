package com.example.dailycalories.presentation.screens.profile.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.dailycalories.R


@Composable
fun ProfileScreen(
    onNavigateToOnboarding: () -> Unit,
    onNavigateToProgress: () -> Unit,
    onNavigateToCalorieIntake: () -> Unit,
) {


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.title_profile),
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(50.dp))
        UnderlinedTextItem(
            text = stringResource(R.string.title_progress),
            icon = Icons.Outlined.KeyboardArrowRight,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onNavigateToProgress()
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