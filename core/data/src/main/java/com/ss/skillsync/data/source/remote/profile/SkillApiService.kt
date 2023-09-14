package com.ss.skillsync.data.source.remote.profile

import com.ss.skillsync.data.source.remote.interceptor.Authenticated
import com.ss.skillsync.data.source.remote.model.auth.UserData
import com.ss.skillsync.data.source.remote.model.skill.GetSkillsResponse
import com.ss.skillsync.data.source.remote.model.skill.UpdateSkillsRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/8/2023.
 */
interface SkillApiService {

    @Authenticated
    @GET("skills/")
    suspend fun getSkills(): Response<GetSkillsResponse>

    @Authenticated
    @PATCH("users/updatePersonalData/")
    suspend fun setUserSkills(
        @Body request: UpdateSkillsRequest,
    ): Response<UserData>
}
