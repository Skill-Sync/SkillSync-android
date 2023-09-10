package com.ss.skillsync.data.source.remote.mentors

import com.ss.skillsync.data.model.MentorDTO
import com.ss.skillsync.data.source.remote.interceptor.Authenticated
import retrofit2.Response
import retrofit2.http.GET

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 10/09/2023
 */
interface MentorApiService {

    @Authenticated
    @GET("suggestedmentors")
    suspend fun getSuggestedMentors(): Response<List<MentorDTO>>
}