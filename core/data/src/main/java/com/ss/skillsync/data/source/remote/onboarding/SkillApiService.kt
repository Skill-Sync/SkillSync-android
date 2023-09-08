package com.ss.skillsync.data.source.remote.onboarding

import com.ss.skillsync.data.model.SkillsDTO
import com.ss.skillsync.data.source.remote.interceptor.Authenticated
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/8/2023.
 */
interface SkillApiService {

    @Authenticated
    @GET("onboarding/skills")
    suspend fun getSkills(): Response<SkillsDTO>

    @Authenticated
    @POST("onboarding/interest_skills")
    suspend fun setInterestedSkills(
        @Body skills: SkillsDTO,
    )

    @Authenticated
    @POST("onboarding/strengths")
    suspend fun setStrengths(
        @Body skills: SkillsDTO,
    )

}