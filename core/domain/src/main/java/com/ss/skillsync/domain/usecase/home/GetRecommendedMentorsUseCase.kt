package com.ss.skillsync.domain.usecase.home

import com.ss.skillsync.domain.BuildConfig
import com.ss.skillsync.domain.repository.MentorRepository
import com.ss.skillsync.model.Mentor
import javax.inject.Inject

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 10/09/2023
 */
class GetRecommendedMentorsUseCase @Inject constructor(
    private val mentorRepository: MentorRepository,
) {
    suspend operator fun invoke(): List<Mentor> {
        return if (BuildConfig.DEBUG) {
            List(10) {
                Mentor(
                    it.toString(),
                    "Mentor $it",
                    "https://picsum.photos/200/300",
                    "Software Engineer",
                )
            }
        } else {
            mentorRepository.getSuggestedMentors()
        }
    }
}