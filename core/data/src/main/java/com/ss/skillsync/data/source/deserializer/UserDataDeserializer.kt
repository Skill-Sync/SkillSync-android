package com.ss.skillsync.data.source.deserializer

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.ss.skillsync.data.source.remote.model.UsersData
import com.ss.skillsync.data.source.remote.model.auth.UserData
import java.lang.reflect.Type

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 11/09/2023
 */
val gson = Gson()
class UserDataDeserializer : JsonDeserializer<UserData> {
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
        return gson.fromJson(content, UserData::class.java)
    }
}

class UsersDataDeserializer : JsonDeserializer<UsersData> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): UsersData {
        val content = json?.asJsonObject?.get("data")?.asJsonArray
        val users = mutableListOf<UserData>()
        content?.forEach {
            val user = gson.fromJson(it, UserData::class.java)
            user.let(users::add)
        }
        return UsersData(users)
    }
}
