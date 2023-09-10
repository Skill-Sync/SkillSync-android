package com.ss.skillsync.data.source.remote.model.skill

import com.google.gson.annotations.SerializedName

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/10/2023.
 */
data class GetSkillsResponse(
    @SerializedName("data")
    val skills: List<GetSkillResponse>,
)

data class GetSkillResponse(
    val _id: String,
    val name: String,
    val description: String,
    val logo: String,
) {
    fun toSkill(): SkillData {
        return SkillData(
            _id = _id,
            name = name,
        )
    }
}
