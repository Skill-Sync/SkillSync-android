package com.ss.skillsync.data.source.remote.onboarding

import com.ss.skillsync.data.model.SkillDTO
import com.ss.skillsync.data.model.SkillsDTO
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
    // caching skills in memory
    private var staticSkillSet: List<SkillDTO>? = null

    // To send one request to get skills
    private var staticSkillDeferred: Deferred<Response<SkillsDTO>>? = null

    suspend fun searchSkills(query: String): List<SkillDTO> {
        return try {
            if (staticSkillSet == null) {
                staticSkillSet = getSkills()
            }
            staticSkillSet?.filter {
                it.name.contains(query, true)
            } ?: emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun getSkills(): List<SkillDTO> {
        return withContext(Dispatchers.IO) {
            if (staticSkillDeferred == null) {
                staticSkillDeferred = async {
                    skillApiService.getSkills()
                }
            }
            try {
                val response = staticSkillDeferred!!.await()
                if (response.isSuccessful) {
                    staticSkillSet = response.body()?.skills
                    staticSkillSet ?: emptyList()
                } else {
                    emptyList()
                }
            } catch (e: Exception) {
                emptyList()
            }
        }
    }

    suspend fun setInterestedSkills(skills: List<SkillDTO>): Boolean {
        return try {
            val request = SkillsDTO(skills)
            skillApiService.setInterestedSkills(request)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun setStrengths(skills: List<SkillDTO>): Boolean {
        return try {
            val request = SkillsDTO(skills)
            skillApiService.setStrengths(request)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
