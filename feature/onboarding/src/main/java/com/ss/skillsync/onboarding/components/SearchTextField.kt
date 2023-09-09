package com.ss.skillsync.onboarding.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ss.skillsync.commonandroid.theme.LightBlack
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.model.Skill


/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 08/09/2023
 */

@Composable
fun SearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    suggestions: Set<Skill>,
    onSkillChose: (Skill) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        var hasFocus by remember {
            mutableStateOf(false)
        }
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 0.5.dp,
                    color = if (hasFocus) MaterialTheme.colorScheme.tertiary else Color.Transparent,
                    shape = if (suggestions.isEmpty())
                        RoundedCornerShape(6.dp)
                    else RoundedCornerShape(
                        topStart = 6.dp,
                        topEnd = 6.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp,
                    ),
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
            ),
            shape = RoundedCornerShape(6.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            trailingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            },
            textStyle = MaterialTheme.typography.bodyLarge
        )
        AnimatedVisibility(visible = suggestions.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(),
                shape = RoundedCornerShape(bottomStart = 6.dp, bottomEnd = 6.dp),
                colors = CardDefaults.cardColors(
                    containerColor = LightBlack,
                    contentColor = Color.White,
                ),
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(12.dp),
                    modifier = Modifier
                        .clip(RoundedCornerShape(bottomStart = 6.dp, bottomEnd = 6.dp)),
                ) {
                    items(
                        items = suggestions.toMutableList(),
                        key = { skill -> skill.id }
                    ) { skillSuggestion ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 40.dp)
                                .clickable {
                                    onSkillChose(skillSuggestion)
                                    onValueChange("")
                                },
                            contentAlignment = Alignment.CenterStart,
                        ) {
                            Text(
                                text = skillSuggestion.name,
                                modifier = Modifier.padding(start = 8.dp),
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        }
                        if (skillSuggestion != suggestions.last()) {
                            Box(
                                modifier = Modifier
                                    .height(1.dp)
                                    .fillMaxWidth()
                                    .background(Color.White.copy(alpha = 0.1f)),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SearchTextFieldPreview() {
    SkillSyncTheme {
        var hasSuggestions by remember {
            mutableStateOf(true)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center,
        ) {
            Button(
                modifier = Modifier.align(Alignment.TopCenter),
                onClick = { hasSuggestions = !hasSuggestions }
            ) {
                Text(text = "Toggle Suggestions")
            }
            SearchTextField(
                value = "",
                onValueChange = {},
                suggestions = if (hasSuggestions) setOf(
                    Skill(name = "Hello"),
                    Skill(name = "World"),
                    Skill(name = "Hello World1"),
                    Skill(name = "Hello World2"),
                    Skill(name = "Hello World3"),
                    Skill(name = "Hello World4"),
                    Skill(name = "Hello World5"),
                ) else emptySet(),
                {/* Placeholder */},
            )
        }
    }
}