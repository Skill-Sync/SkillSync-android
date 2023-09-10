package com.ss.skillsync.data.repository

import com.ss.skillsync.data.source.remote.mentors.MentorRemoteSource
import com.ss.skillsync.domain.repository.MentorRepository
import com.ss.skillsync.model.Mentor
import javax.inject.Inject

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 10/09/2023
 */
class MentorRepositoryImpl @Inject constructor(
    private val mentorRemoteSource: MentorRemoteSource
) : MentorRepository {

    override suspend fun getSuggestedMentors(): List<Mentor> {
        return mentorRemoteSource.getSuggestedMentors()
    }
}