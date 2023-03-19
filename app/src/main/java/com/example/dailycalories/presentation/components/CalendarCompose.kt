package com.example.dailycalories.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dailycalories.utils.*
import java.text.SimpleDateFormat
import java.util.*


//IMPLEMENTATION BY https://github.com/fcat97/CalendarCompose
@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun CalendarCompose(
    modifier: Modifier = Modifier,
    selectedDate: Date = Date(),
    onDateChange: (y: Int, m: Int, d: Int) -> Unit = { _, _, _ -> },
) {

    var showingMonth by remember {
        mutableStateOf(Calendar.getInstance()[Calendar.MONTH] + 1)
    }
    var showingYear by remember {
        mutableStateOf(Calendar.getInstance()[Calendar.YEAR])
    }
    val currentMonthYearText by remember(showingMonth, showingYear) {
        mutableStateOf(
            getMonthName(showingMonth, showingYear) + " " + String.format(
                "%4d",
                showingYear
            )
        )
    }
    val showingDates by remember(showingMonth, showingYear) {
        mutableStateOf(getMonthDates(showingMonth, showingYear))
    }
    val showSelectedDateText by remember(selectedDate, showingMonth) {
        derivedStateOf {
            Calendar.getInstance().apply {
                time = selectedDate
            }.let {
                // true if showing month and year is different from selected date's month and year
                showingMonth != it[Calendar.MONTH] + 1 || showingYear != it[Calendar.YEAR]
            }
        }
    }
    val selectedDateText by remember(selectedDate) {
        derivedStateOf {
            SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault()).format(selectedDate)
        }
    }

    Column(modifier = modifier.fillMaxWidth()) {
        // top bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // previous month button
            IconButton(onClick = {
                if (showingMonth == 1) {
                    showingYear -= 1
                    showingMonth = 12
                } else showingMonth -= 1
            }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "previous month",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colors.onSurface
                )
            }

            Column(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(25))
                    .clickable {
                        // take to selected date's month
                        Calendar
                            .getInstance()
                            .apply {
                                time = selectedDate
                            }
                            .let {
                                showingMonth = it[Calendar.MONTH] + 1
                                showingYear = it[Calendar.YEAR]
                            }
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = currentMonthYearText, color = MaterialTheme.colors.onSurface)

                AnimatedVisibility(visible = showSelectedDateText) {
                    Text(
                        text = selectedDateText,
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.secondary
                    )
                }
            }

            IconButton(onClick = {
                if (showingMonth == 12) {
                    showingYear += 1
                    showingMonth = 1
                } else showingMonth += 1
            }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "next month",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colors.onSurface
                )
            }
        }

        // dates
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                for (i in 1..7) {
                    ItemDate(
                        text = stringResource(id = getWeekDayTextResId(i)),
                        onClick = {},
                        textColor = MaterialTheme.colors.onSurface
                    )
                }
            }

            for (r in 1..6) {
                val st = (r - 1) * 7
                val weekDates = showingDates.subList(st, st + 7)

                if (weekDates.any { it != 0 }) {
                    /*LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        items(items = weekDates) { d ->
                            ItemDate(
                                text = if (d == 0) "" else d.toString(),
                                textColor = if (d == todayDate && showingMonth == thisMonth && showingYear == thisYear) {
                                    MaterialTheme.colors.primary
                                } else Color.Unspecified,
                                isSelected = selectedDate.isSelected(d, showingMonth, showingYear),
                                onClick = {
                                    if (d != 0) onDateChange(showingYear, showingMonth, d)
                                },
                            )
                        }
                    }*/
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        for (d in weekDates) {
                            ItemDate(
                                text = if (d == 0) EMPTY_STRING else d.toString(),
                                textColor = if (d == todayDate && showingMonth == thisMonth && showingYear == thisYear) {
                                    MaterialTheme.colors.secondary
                                } else MaterialTheme.colors.onSurface,
                                isSelected = selectedDate.isSelected(d, showingMonth, showingYear),
                                onClick = {
                                    if (d != 0) onDateChange(showingYear, showingMonth, d)
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ItemDate(
    text: String,
    textColor: Color = Color.Unspecified,
    isSelected: Boolean = false,
    onClick: (d: String) -> Unit,
) {
    Surface(
        modifier = Modifier.requiredSize(32.dp),
        color = if (isSelected) MaterialTheme.colors.secondary else Color.Transparent,
        shape = CircleShape,
        elevation = if (isSelected) 2.dp else 0.dp,
        onClick = { onClick(text) }
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = text,
                color = if (isSelected) MaterialTheme.colors.onSecondary else textColor
            )
        }
    }


}