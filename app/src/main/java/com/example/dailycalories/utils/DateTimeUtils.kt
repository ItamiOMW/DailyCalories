package com.example.dailycalories.utils

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*


const val DATE_FORMAT = "yyyy-MM-dd"

const val TIME_FORMAT = "HH:mm"


fun getCurrentDateString(): String {
    return LocalDate.now().toString()
}

fun getCurrentTimeSeconds(): Long {
    return LocalTime.now().toSecondOfDay().toLong()
}

fun getDateDaysInAdvance(daysToAdd: Long): String {
    return LocalDate.now().plusDays(daysToAdd).toString()
}

fun String.formatDate(): String {
    val dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
    val date = LocalDate.parse(this, dateFormatter)
    val month = date.month.getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault())
    val year = date.year.toString()
    val dayOfMonth = date.dayOfMonth.toString()
    return "$dayOfMonth $month, $year"
}