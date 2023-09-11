package com.ss.skillsync.data.source.remote.model.auth

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import com.ss.skillsync.data.source.remote.model.skill.SkillData
import com.ss.skillsync.data.source.remote.model.skill.SkillLearned
import java.lang.reflect.Type


data class UserData(
    val _id: String?,
    val id: String?,
    val about: String?,
    val active: Boolean?,
    val email: String?,
    val isEmployed: Boolean?,
    @SerializedName("onboarding_completed")
    val onboardingCompleted: Boolean?,
    val name: String?,
    val role: String?,
    val skillsLearned: List<SkillLearned>?,
    val skillsToLearn: List<SkillData>?,
    val accessJWT: String?,
    val refreshJWT: String?,
    val photo: String?
) {
    fun tokensAvailable() = !accessJWT.isNullOrEmpty() && !refreshJWT.isNullOrEmpty()
}

class UserDeserializer: JsonDeserializer<UserData> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): UserData {
        val content = json?.asJsonObject?.get("data")?.asJsonObject
        val accessToken = json?.asJsonObject?.getAsJsonPrimitive("accessJWT")
        val refreshJWT = json?.asJsonObject?.getAsJsonPrimitive("refreshJWT")
        content?.add("accessJWT", accessToken)
        content?.add("refreshJWT", refreshJWT)
        return Gson().fromJson(content, UserData::class.java)
    }

}