package com.ss.skillsync.domain.usecase

import com.ss.skillsync.domain.repository.MentorRepository
import com.ss.skillsync.model.Mentor
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/11/2023.
 */
class SelectMentorUseCase @Inject constructor(
    private val mentorRepository: MentorRepository,
) {
    operator fun invoke(mentor: Mentor) {
        mentorRepository.selectedMentor = mentor
    }
}
