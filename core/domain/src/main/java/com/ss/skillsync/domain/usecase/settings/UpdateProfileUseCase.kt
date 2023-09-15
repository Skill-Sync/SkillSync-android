package com.ss.skillsync.domain.usecase.settings

import com.ss.skillsync.domain.repository.SkillRepository
import com.ss.skillsync.domain.repository.UserRepository
import com.ss.skillsync.model.User
import javax.inject.Inject

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 14/09/2023
 */
class UpdateProfileUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val skillRepository: SkillRepository,
) {
    suspend operator fun invoke(user: User): Result<Unit> {
        return userRepository.updatePersonalData(user).onSuccess {
            if (user.interestedSkills.isNotEmpty() || user.strengths.isNotEmpty()) {
                skillRepository.setUserSkills(
                    interestedSkills = user.interestedSkills,
                    strengthSkills = user.strengths
                ).onSuccess {
                    return Result.success(Unit)
                }.onFailure {
                    return Result.failure(it)
                }
            }
        }
    }
}