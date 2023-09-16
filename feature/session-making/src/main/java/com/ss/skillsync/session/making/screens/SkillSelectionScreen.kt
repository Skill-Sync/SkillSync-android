package com.ss.skillsync.session.making.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ss.skillsync.commonandroid.components.RoundedTextFieldWithTitle
import com.ss.skillsync.commonandroid.components.ScreenColumn
import com.ss.skillsync.commonandroid.components.SecondaryActionBrandButton
import com.ss.skillsync.commonandroid.theme.LightBlack
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.model.Skill
import com.ss.skillsync.session.making.R
import com.ss.skillsync.session.making.SessionMakingState
import com.ss.skillsync.session.making.SessionMakingUIEvent

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/14/2023.
 */

@Composable
fun SkillSelectionScreen(
    modifier: Modifier = Modifier,
    state: SessionMakingState,
    onEvent: (SessionMakingUIEvent) -> Unit,
) {
    ScreenColumn(
        modifier = modifier,
        screenLabel = stringResource(R.string.choose_what_you_want_to_learn),
        arrangement = Arrangement.SpaceBetween,
    ) {
        Spacer(modifier = Modifier.weight(0.5f))
        Box(
            modifier = Modifier.weight(1.5f),
        ) {
            SearchBarSection(
                searchQuery = state.searchQuery,
                onSearchQueryChanged = { onEvent(SessionMakingUIEvent.OnSearchQueryChanged(it)) },
                suggestions = state.searchResult,
                selectedSkill = state.selectedSkill,
                onSkillSelected = { onEvent(SessionMakingUIEvent.OnSkillSelected(it)) }
            )
        }
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            SecondaryActionBrandButton(
                text = stringResource(R.string.start_search),
                onClick = {
                    onEvent(SessionMakingUIEvent.OnStartSearchingClicked)
                },
                enabled = state.canStartSearching,
            )
        }

    }
}

@Composable
private fun SearchBarSection(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    suggestions: List<Skill>,
    selectedSkill: Skill? = null,
    onSkillSelected: (Skill) -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        RoundedTextFieldWithTitle(
            title = "Choose your skill",
            value = selectedSkill?.name ?: searchQuery,
            onValueChange = onSearchQueryChanged
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
                        items = suggestions,
                        key = { skill -> skill.id }
                    ) { skillSuggestion ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 40.dp)
                                .clickable {
                                    onSkillSelected(skillSuggestion)
                                    onSearchQueryChanged(skillSuggestion.name)
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
fun SearchBarSectionPreview() {
    SkillSyncTheme {
        val skills = List(10) {
            Skill(
                id = it.toString(),
                name = "Android"
            )
        }
        SkillSelectionScreen(
            state = SessionMakingState(
                searchQuery = "Android",
                searchResult = skills,
                selectedSkill = skills.first()
            ),
            onEvent = {}
        )
    }
}
