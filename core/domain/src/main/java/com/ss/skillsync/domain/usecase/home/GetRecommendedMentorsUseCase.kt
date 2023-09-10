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
            return listOf(
                Mentor(
                    "Mohannad El-Sayeh",
                    "https://avatars.githubusercontent.com/u/24874166?v=4",
                    "Android"
                ),
                Mentor(
                    "Mohannad El-Sayeh",
                    "https://avatars.githubusercontent.com/u/24874166?v=4",
                    "Java"
                ),
                Mentor(
                    "Mohannad El-Sayeh",
                    "https://avatars.githubusercontent.com/u/24874166?v=4",
                    "Kotlin"
                ),
                Mentor(
                    "Mohannad El-Sayeh",
                    "https://avatars.githubusercontent.com/u/24874166?v=4",
                    "Graphics Design"
                ),
                Mentor(
                    "Mohannad El-Sayeh",
                    "https://avatars.githubusercontent.com/u/24874166?v=4",
                    "UI/UX"
                ),
                Mentor(
                    "Mohannad El-Sayeh",
                    "https://avatars.githubusercontent.com/u/24874166?v=4",
                    "Android"
                ),
            )
        } else {
            mentorRepository.getSuggestedMentors()
        }
    }
}