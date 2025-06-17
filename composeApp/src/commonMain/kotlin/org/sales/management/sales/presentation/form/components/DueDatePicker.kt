package org.sales.management.sales.presentation.form.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DueDatePicker(
    selectedDate: LocalDate?,
    onDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }

    val displayDate = selectedDate?.toString() ?: "Selecionar Data"

    Box(modifier = modifier) {
        OutlinedTextField(
            value = displayDate,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showDialog = true },
            label = { Text("Vencimento") },
            enabled = false,
            trailingIcon = {
                Icon(Icons.Default.DateRange, contentDescription = null)
            }
        )

        if (showDialog) {
            val datePickerState = rememberDatePickerState(
                selectableDates = object : SelectableDates {
                    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                        val date = Instant.fromEpochMilliseconds(utcTimeMillis)
                            .toLocalDateTime(TimeZone.currentSystemDefault()).date
                        return validateDate(date, RestrictionType.NoPastDates)
                    }
                }
            )

            DatePickerDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            val millis = datePickerState.selectedDateMillis
                            if (millis != null) {
                                val date = Instant.fromEpochMilliseconds(millis)
                                    .toLocalDateTime(TimeZone.currentSystemDefault()).date

                                if (validateDate(date, RestrictionType.NoPastDates)) {
                                    onDateSelected(date)
                                    showDialog = false
                                    showError = false
                                } else {
                                    showError = true
                                }
                            }
                        }
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancelar")
                    }
                }
            ) {
                Column {
                    DatePicker(state = datePickerState)
                    if (showError) {
                        Text(
                            "Data inválida. Escolha uma data a partir de hoje.",
                            color = Color.Red,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

private fun validateDate(date: LocalDate, restrictionType: RestrictionType): Boolean {
    val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    return when (restrictionType) {
        RestrictionType.NoPastDates -> date >= today
        RestrictionType.None -> true
    }
}

enum class RestrictionType {
    None,
    NoPastDates
}
