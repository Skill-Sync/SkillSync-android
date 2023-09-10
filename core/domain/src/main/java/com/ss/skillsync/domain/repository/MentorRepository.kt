package com.ss.skillsync.domain.repository

import com.ss.skillsync.model.Mentor

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 10/09/2023
 */
interface MentorRepository {

    suspend fun getSuggestedMentors(): List<Mentor>
}