package com.example.dailycalories.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.dailycalories.R


@Composable
fun DialogExit(
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.title_exit),
    text: String = stringResource(R.string.warning_exit),
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {

    Dialog(
        onDismissRequest = { onDismiss() }
    ) {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(15.dp),
            elevation = 5.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.body1
                )
                Row(
                    modifier = Modifier.align(Alignment.End),
                    horizontalArrangement = Arrangement.spacedBy(30.dp)
                ) {
                    TextButton(
                        onClick = {
                            onDismiss()
                        }
                    ) {
                        Text(text = stringResource(R.string.title_no))
                    }
                    TextButton(
                        onClick = {
                            onConfirm()
                        }
                    ) {
                        Text(text = stringResource(R.string.title_yes))
                    }
                }
            }
        }
    }

}