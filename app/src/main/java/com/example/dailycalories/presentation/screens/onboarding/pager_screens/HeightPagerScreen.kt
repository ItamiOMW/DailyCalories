package com.example.dailycalories.presentation.screens.onboarding.pager_screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.dailycalories.R
import com.example.dailycalories.presentation.theme.ui.White


@Composable
fun HeightPagerScreen(
    height: Int,
    onNextClicked: (height: Int) -> Unit,
    onPreviousClicked: () -> Unit,
) {

    val enteredHeight = rememberSaveable() {
        mutableStateOf(height)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = {
                    onPreviousClicked()
                },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(Icons.Outlined.KeyboardArrowLeft, null)
            }
            Text(
                text = stringResource(id = R.string.title_height_cm),
                style = MaterialTheme.typography.h4.copy(textAlign = TextAlign.Center),
                modifier = Modifier.align(Alignment.TopCenter),
            )
        }
        TextField(
            value = enteredHeight.value.toString(),
            onValueChange = { newHeight ->
                enteredHeight.value = newHeight.toIntOrNull() ?: 0
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        )
        FloatingActionButton(
            backgroundColor = MaterialTheme.colors.secondaryVariant,
            contentColor = White,
            onClick = {
                onNextClicked(enteredHeight.value)
            },
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 10.dp)
        ) {
            Icon(
                Icons.Outlined.KeyboardArrowRight,
                stringResource(R.string.desc_navigate_to_previous)
            )
        }
    }

}