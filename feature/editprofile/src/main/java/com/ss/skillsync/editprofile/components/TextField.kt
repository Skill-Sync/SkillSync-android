package com.ss.skillsync.editprofile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ss.skillsync.commonandroid.theme.LightBlack
import com.ss.skillsync.commonandroid.theme.OsloGrey
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 14/09/2023
 */

@Composable
fun EditTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Next,
) {
    var hasFocus by remember {
        mutableStateOf(false)
    }
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .border(
                width = 0.5.dp,
                color = if (hasFocus) MaterialTheme.colorScheme.tertiary else Color.Transparent,
                shape = RoundedCornerShape(9.dp),
            )
            .onFocusChanged { focusState ->
                hasFocus = focusState.isFocused
            },
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = LightBlack,
            focusedContainerColor = LightBlack,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
        ),
        shape = RoundedCornerShape(9.dp),
        keyboardOptions = KeyboardOptions(imeAction = imeAction),
        textStyle = MaterialTheme.typography.bodyLarge,
    )
}

@Composable
fun EditTextFieldWithTitle(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Next,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start,
        modifier = modifier
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
            color = OsloGrey,
        )
        EditTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            imeAction = imeAction
        )
    }
}

@Preview
@Composable
fun EditTextFieldPreview() {
    SkillSyncTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            EditTextField("Mohannad El-Sayeh", {})
            EditTextFieldWithTitle(label = "Name", value = "Mohannad El-Sayeh", onValueChange = {})
        }
    }
}