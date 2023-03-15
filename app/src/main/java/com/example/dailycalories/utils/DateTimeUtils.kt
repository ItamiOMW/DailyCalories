package com.example.dailycalories.utils

import com.example.dailycalories.R
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*


const val DATE_FORMAT = "yyyy-MM-dd"

const val TIME_FORMAT = "HH:mm"


fun getCurrentDateString(): String {
    return LocalDate.now().toString()
}

fun getTomorrowDateString(): String {
    return LocalDate.now().plusDays(1).toString()
}

fun getYesterdayDateString(): String {
    return LocalDate.now().minusDays(1).toString()
}

fun getDateOfDayNDaysAgo(nDays: Long, date: String): String {
    val dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
    val localDate = LocalDate.parse(date, dateFormatter)
    return localDate.minusDays(nDays).toString()
}

fun getDateOfDayNDaysInAdvance(nDays: Long, date: String): String {
    val dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
    val localDate = LocalDate.parse(date, dateFormatter)
    return localDate.plusDays(nDays).toString()
}

fun String.formatDateToMillis(): Long {
    val dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
    val date = LocalDate.parse(this, dateFormatter)
    val zoneId = ZoneId.systemDefault()
    return date.atStartOfDay(zoneId).toEpochSecond() * 1000
}

fun formatDateToString(dayOfMonth: Int, month: Int, year: Int): String {
    return LocalDate.of(year, month, dayOfMonth).toString()
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
    val dayOfWeek = date.dayOfWeek.getDisplayName(
        TextStyle.FULL_STANDALONE, Locale.getDefault()
    ).uppercase()
    val dayOfMonth = date.dayOfMonth.toString()
    return "$dayOfWeek, $dayOfMonth $month"
}

fun String.formatDateWithoutDayOfWeek(): String {
    val dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
    val date = LocalDate.parse(this, dateFormatter)
    val month = date.month.getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault())
    val dayOfMonth = date.dayOfMonth.toString()
    return "$dayOfMonth $month"
}

fun Long.formatTimeToString(): String {
    return LocalTime.ofSecondOfDay(
        this
    ).format(DateTimeFormatter.ofPattern(TIME_FORMAT))
}


internal fun Date.isSelected(date: Int, showingMonth: Int, showingYear: Int): Boolean {
    val c = Calendar.getInstance().apply { time = this@isSelected }
    return c[Calendar.DAY_OF_MONTH] == date && c[Calendar.MONTH] == showingMonth - 1 && c[Calendar.YEAR] == showingYear
}
internal fun getMonthDates(month: Int, year: Int): List<Int> {
    val c = Calendar.getInstance().apply {
        this[Calendar.DAY_OF_MONTH] = 1
        this[Calendar.MONTH] = month - 1
        this[Calendar.YEAR] = year
    }

    val starting = c[Calendar.DAY_OF_WEEK] - 1

    val days = mutableListOf<Int>()
    days += (-1 * starting until 0).map { 0 }
    days += (1..c.getActualMaximum(Calendar.DAY_OF_MONTH))
    days += (days.size..42).map { 0 }

    return days
}
internal fun getWeekDayTextResId(dayOfWeek: Int): Int = when(dayOfWeek) {
    1 -> R.string.title_sunday_short
    2 -> R.string.title_monday_short
    3 -> R.string.title_tuesday_short
    4 -> R.string.title_wednesday_short
    5 -> R.string.title_thursday_short
    6 -> R.string.title_friday_short
    else -> R.string.title_saturday_short
}

private val monthFormat = SimpleDateFormat("MMMM", Locale.getDefault())
internal fun getMonthName(m: Int, y: Int) = Calendar.getInstance().apply {
    set(y, m - 1, 1)
}.let {
    monthFormat.format(it.time)
}

internal val today = Calendar.getInstance()
internal val todayDate get() = today[Calendar.DAY_OF_MONTH]
internal val thisMonth get() = today[Calendar.MONTH] + 1
internal val thisYear get() = today[Calendar.YEAR]