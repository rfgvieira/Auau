package com.rfgvieira.auau.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.unit.dp
import com.rfgvieira.auau.utils.DateUtils.Companion.toDateFormat



/*TODO: Mostrar só datas anteriores*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateInput(
    state: MutableState<String>,
    isValid: MutableState<Boolean>,
    focusManager: FocusManager,
    placeholder: String,
    onDatePicked: (String) -> Unit
) {
    val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates{
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return utcTimeMillis <= System.currentTimeMillis()
        }
    })
    var showDatePickerDialog by remember { mutableStateOf(false) }

    if (showDatePickerDialog) {
        DatePickerDialog(onDismissRequest = { showDatePickerDialog = false }, confirmButton = {
            Button(onClick = {
                datePickerState.selectedDateMillis?.let {
                    onDatePicked(it.toDateFormat())
                    isValid.value = true
                }
                focusManager.moveFocus(FocusDirection.Down)
                showDatePickerDialog = false
            }) {
                Text(text = "Selected Date")
            }
        }) {
            DatePicker(state = datePickerState)
        }
    }

    TextField(
        value = state.value,
        onValueChange = { },
        Modifier
            .padding(vertical = 16.dp)
            .onFocusEvent {
                if (it.isFocused) {
                    showDatePickerDialog = true

                }
            },
        placeholder = {
            Text(text = placeholder, color = MaterialTheme.colorScheme.outline)
        },
        readOnly = true,
        isError = !isValid.value
    )
}