package com.example.dailycalories.presentation.screens.onboarding.pager_screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.dailycalories.R
import com.example.dailycalories.domain.model.user.Gender
import com.example.dailycalories.presentation.theme.ui.White


@Composable
fun GenderPagerScreen(
    gender: Gender,
    onNextClicked: (Gender) -> Unit,
    onPreviousClicked: () -> Unit,
) {

    val selectedGenderType = rememberSaveable(key = gender.type) {
        mutableStateOf(gender.type)
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
                text = stringResource(id = R.string.title_gender),
                style = MaterialTheme.typography.h4.copy(textAlign = TextAlign.Center),
                modifier = Modifier.align(Alignment.TopCenter),
            )
        }
        GendersSection(
            selectedGenderType = selectedGenderType.value,
            onGenderClicked = { clickedGender ->
                selectedGenderType.value = clickedGender.type
            }
        )
        FloatingActionButton(
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = White,
            onClick = {
                onNextClicked(Gender.fromString(selectedGenderType.value))
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


@Composable
private fun GendersSection(
    selectedGenderType: String,
    onGenderClicked: (Gender) -> Unit,
) {
    val genders = listOf(
        Gender.Male,
        Gender.Female,
    )
    Column() {
        genders.forEach { gender ->
            GenderItem(
                isSelected = selectedGenderType == gender.type,
                gender = gender,
                onGenderClicked = {
                    onGenderClicked(gender)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun GenderItem(
    isSelected: Boolean,
    gender: Gender,
    onGenderClicked: (Gender) -> Unit,
) {
    Card(
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        backgroundColor = if (isSelected) MaterialTheme.colors.surface else Color.Transparent,
        onClick = {
            onGenderClicked(gender)
        },
    ) {
        Text(
            text = stringResource(id = gender.nameResId),
            style = MaterialTheme.typography.h4.copy(textAlign = TextAlign.Center),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 25.dp, bottom = 25.dp),
            color = MaterialTheme.colors.onSurface
        )
    }
}