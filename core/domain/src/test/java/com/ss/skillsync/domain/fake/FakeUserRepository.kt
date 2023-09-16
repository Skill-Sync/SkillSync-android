package com.ss.skillsync.domain.fake

import com.ss.skillsync.domain.payload.SignInPayload
import com.ss.skillsync.domain.payload.SignUpPayload
import com.ss.skillsync.domain.repository.UserRepository
import com.ss.skillsync.domain.util.StringUtil
import com.ss.skillsync.model.Mentor
import com.ss.skillsync.model.Skill
import com.ss.skillsync.model.SkillLevel
import com.ss.skillsync.model.User

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/8/2023.
 */
class FakeUserRepository : UserRepository {

    private var activeUser: User? = null
    private var isFirstOpen = true

    override suspend fun getActiveUser(): Result<User> {
        return if (activeUser != null) {
            Result.success(activeUser!!)
        } else {
            Result.failure(Exception("User not found"))
        }
    }

    override suspend fun signIn(signInPayload: SignInPayload): Result<User> {
        val dummyUser = User(
            name = "FakeUser",
            email = "fakeuser@example.com",
            about = "This is a fake user",
            profilePictureUrl = "https://example.com/fakeuser.jpg",
            onboardingCompleted = true,
            interestedSkills = listOf(
                Skill(id = "1", name = "Programming", level = SkillLevel.ADVANCED),
                Skill(id = "2", name = "Design", level = SkillLevel.INTERMEDIATE),
            ),
            strengths = listOf(
                Skill(id = "3", name = "Communication", level = SkillLevel.BEGINNER),
            ),
        )
        activeUser = dummyUser
        return Result.success(dummyUser)
    }

    override suspend fun signUp(signUpPayload: SignUpPayload): Result<Unit> {
        return Result.success(Unit)
    }

    override suspend fun signOut() {
        activeUser = null
    }

    override suspend fun isFirstOpen(): Boolean {
        return isFirstOpen.also { isFirstOpen = false }
    }

    override suspend fun getActiveUserAsMentor(): Result<Mentor> {
        return if (activeUser != null) {
            Result.success(
                Mentor(
                    name = activeUser!!.name,
                    email = activeUser!!.email,
                    about = activeUser!!.about,
                )
            )
        } else {
            Result.failure(Exception("User not found"))
        }
    }

    override suspend fun getRecommendedMentors(): List<Mentor> {
        return List(10) {
            Mentor(
                name = "Fake Mentor $it",
                email = "fake1mentor$it@gmail.com",
                about = "This is a fake mentor",
                pictureUrl = StringUtil.getRandomImageUrl(),
            )
        }
    }

    override suspend fun updatePersonalData(user: User): Result<Unit> {
        activeUser = user
        return Result.success(Unit)
    }
}
