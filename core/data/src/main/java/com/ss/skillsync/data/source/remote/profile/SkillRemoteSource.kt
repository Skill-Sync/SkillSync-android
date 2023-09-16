package com.ss.skillsync.data.source.remote.profile

import com.ss.skillsync.data.mapper.toSkillLearnedRequest
import com.ss.skillsync.data.source.remote.model.auth.UserData
import com.ss.skillsync.data.source.remote.model.skill.GetSkillsResponse
import com.ss.skillsync.data.source.remote.model.skill.SkillData
import com.ss.skillsync.data.source.remote.model.skill.UpdateSkillsRequest
import com.ss.skillsync.model.Skill
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/8/2023.
 */
class SkillRemoteSource @Inject constructor(
    private val skillApiService: SkillApiService,
) {

    companion object {
        private const val TAG = "SkillRemoteSource"
    }
    // caching skills in memory
    private var staticSkillSet: List<SkillData>? = null

    // To send one request to get skills
    private var staticSkillDeferred: Deferred<Response<GetSkillsResponse>>? = null

    suspend fun searchSkills(query: String): List<SkillData> {
        return try {
            if (staticSkillSet == null) {
                staticSkillSet = getSkills()
            }
            staticSkillSet?.filter {
                it.name.contains(query, ignoreCase = true)
            } ?: emptyList()
        } catch (e: Exception) {
            com.timers.stopwatch.core.log.error(TAG, e)
            emptyList()
        }
    }

    suspend fun getSkills(): List<SkillData> {
        return withContext(Dispatchers.IO) {
            if (staticSkillDeferred == null) {
                staticSkillDeferred = async {
                    skillApiService.getSkills()
                }
            }
            try {
                val response = staticSkillDeferred!!.await()
                if (response.isSuccessful) {
                    staticSkillSet = response.body()?.skills?.map { it.toSkill() }
                    staticSkillSet ?: emptyList()
                } else {
                    emptyList()
                }
            } catch (e: Exception) {
                com.timers.stopwatch.core.log.error(TAG, e)
                emptyList()
            }
        }
    }

    suspend fun setUserSkills(
        skillsToLearn: List<Skill>,
        skillsLearned: List<Skill>,
    ): Result<UserData> {
        return withContext(Dispatchers.IO) {
            try {
                val request = UpdateSkillsRequest(
                    skillsToLearn.map { it.id },
                    skillsLearned.map { it.toSkillLearnedRequest() },
                )
                val response = skillApiService.setUserSkills(request)
                if (response.isSuccessful) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Throwable(response.message()))
                }
            } catch (e: Exception) {
                com.timers.stopwatch.core.log.error(TAG, e)
                Result.failure(e)
            }
        }
    }
}
