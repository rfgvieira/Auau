package com.rfgvieira.auau.presentation.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import com.rfgvieira.auau.utils.EnumUtils

@Composable
fun TextInput(
    focusManager: FocusManager,
    state: String,
    isValid: Boolean? = null,
    placeholder: String,
    option: EnumUtils.KeyboardOptions,
    modifier: Modifier,
    onTextInputted : (String) -> Unit
) {
    TextField(
        value = state,
        onValueChange = { input ->
            onTextInputted(input)

        },
        modifier = modifier,
        placeholder = {
            Text(
                text = placeholder,
                color = MaterialTheme.colorScheme.outline
            )
        },
        keyboardOptions = when (option) {
            EnumUtils.KeyboardOptions.NEXT -> {
                KeyboardOptions(
                    imeAction = ImeAction.Next,
                    capitalization = KeyboardCapitalization.Sentences
                )
            }

            EnumUtils.KeyboardOptions.DONE -> {
                KeyboardOptions(
                    imeAction = ImeAction.Done,
                    capitalization = KeyboardCapitalization.Sentences
                )
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

        },
        isError = isValid?.let{
            !it
        } ?: false
    )
}
