package com.ss.skillsync.commonandroid.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.ss.skillsync.commonandroid.R

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/7/2023.
 */

@Composable
fun RoundedTextFieldWithTitle(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        TextFieldTitle(title = title)
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            ),
            singleLine = true,
            shape = MaterialTheme.shapes.small,
        )
    }
}

@Composable
fun RoundedPasswordTextField(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
) {
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }
    Column(modifier = modifier.fillMaxWidth()) {
        TextFieldTitle(title = title)
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = value, onValueChange = onValueChange,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val iconRes =
                    if (isPasswordVisible) R.drawable.ic_eye_invisible else R.drawable.ic_eye_visible
                IconButton(
                    onClick = {
                        isPasswordVisible = !isPasswordVisible
                    },
                ) {
                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = "Toggle password visibility",
                    )
                }
            },
            singleLine = true,
            shape = MaterialTheme.shapes.small,
        )
    }
}

@Composable
private fun TextFieldTitle(title: String) {
    Text(
        modifier = Modifier.padding(start = 5.dp, bottom = 5.dp),
        text = title,
        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.onBackground,
    )
    Spacer(modifier = Modifier.height(2.dp))
}
