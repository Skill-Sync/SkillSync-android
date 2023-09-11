package com.ss.skillsync.data.source.remote.mentors

import com.ss.skillsync.model.Mentor
import javax.inject.Inject

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 10/09/2023
 */
class MentorRemoteSource @Inject constructor(
    private val mentorApiService: MentorApiService
) {
    suspend fun getSuggestedMentors(): List<Mentor> {
        if (mentorApiService.getSuggestedMentors().isSuccessful) {
            return mentorApiService.getSuggestedMentors().body()!!.map {
                Mentor(
                    it.id,
                    it.name,
                    it.pictureUrl,
                    it.field
                )
            }
        } else {
            throw Exception("Failed to get suggested mentors")
        }
    }
}