package com.ss.skillsync.domain.usecase.session

import com.ss.skillsync.domain.repository.UserRepository
import com.ss.skillsync.model.Mentor
import javax.inject.Inject

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 10/09/2023
 */
class GetRecommendedMentorsUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(): List<Mentor> {
        return userRepository.getRecommendedMentors()
    }
}
