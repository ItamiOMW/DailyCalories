package com.example.dailycalories.presentation.components

import android.app.TimePickerDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.dailycalories.R
import com.example.dailycalories.utils.formatTimeToLong
import java.util.Calendar.*


@Composable
fun TimePickerDialog(
    showDialog: Boolean,
    onCancelled: () -> Unit,
    onTimePicked: (time: Long) -> Unit,
) {
    val context = LocalContext.current

    val calendar by remember {
        mutableStateOf(getInstance())
    }
    val hour by remember {
        mutableStateOf(calendar[HOUR_OF_DAY])
    }
    val minute by remember {
        mutableStateOf(calendar[MINUTE])
    }

    val timePickerDialog = TimePickerDialog(
        context,
        R.style.DialogTheme,
        { _, hour: Int, minute: Int ->
            onTimePicked(formatTimeToLong(hour, minute))
        },
        hour, minute, true
    )

    timePickerDialog.setOnCancelListener {
        onCancelled.invoke()
    }

    if (showDialog) {
        timePickerDialog.show()
    }

}