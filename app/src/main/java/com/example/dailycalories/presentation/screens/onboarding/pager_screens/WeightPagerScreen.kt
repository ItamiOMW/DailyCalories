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
fun WeightPagerScreen(
    weight: Float,
    onNextClicked: (weight: Float) -> Unit,
    onPreviousClicked: () -> Unit,
) {


    val enteredWeight = rememberSaveable() {
        mutableStateOf(weight.toString())
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
                text = stringResource(id = R.string.title_weight_kg),
                style = MaterialTheme.typography.h4.copy(textAlign = TextAlign.Center),
                modifier = Modifier.align(Alignment.TopCenter),
            )
        }
        TextField(
            value = enteredWeight.value,
            onValueChange = { newHeight ->
                enteredWeight.value = newHeight
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        )
        FloatingActionButton(
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = White,
            onClick = {
                if (enteredWeight.value.isNotBlank()) {
                    onNextClicked(enteredWeight.value.toFloat())
                } else {
                    onNextClicked(0.0f)
                }

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