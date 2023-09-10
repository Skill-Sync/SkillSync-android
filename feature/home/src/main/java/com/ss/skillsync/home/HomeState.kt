package com.ss.skillsync.home

import com.ss.skillsync.model.Mentor

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 10/09/2023
 */
data class HomeState(
    val suggestedMentors: List<Mentor> = emptyList(),

) {
    val isLoading: Boolean
        get() = suggestedMentors.isEmpty()
}
