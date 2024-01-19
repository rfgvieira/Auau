package com.rfgvieira.auau.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.rfgvieira.auau.EnumUtils

@Composable
fun TextInput(
    focusManager: FocusManager,
    state: MutableState<String>,
    placeholder: String,
    option: EnumUtils.KeyboardOptions
) {
    TextField(
        value = state.value,
        onValueChange = { state.value = it },
        modifier = Modifier.padding(vertical = 16.dp),
        placeholder = {
            Text(
                text = placeholder,
                color = MaterialTheme.colorScheme.outline
            )
        },
        keyboardOptions = when (option) {
            EnumUtils.KeyboardOptions.NEXT -> {
                KeyboardOptions(imeAction = ImeAction.Next, capitalization = KeyboardCapitalization.Sentences)
            }

            EnumUtils.KeyboardOptions.DONE -> {
                KeyboardOptions(imeAction = ImeAction.Done, capitalization = KeyboardCapitalization.Sentences)
            }
        },
        keyboardActions =
        when (option) {
            EnumUtils.KeyboardOptions.NEXT -> {
                KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            }

            EnumUtils.KeyboardOptions.DONE -> {
                KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                )
            }

        }
    )
}
