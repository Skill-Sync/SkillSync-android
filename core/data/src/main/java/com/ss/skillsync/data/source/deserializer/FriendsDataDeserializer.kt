package com.ss.skillsync.data.source.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.ss.skillsync.data.source.remote.model.FriendsData
import com.ss.skillsync.data.source.remote.model.auth.UserData
import java.lang.reflect.Type

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/16/2023.
 */
class FriendsDataDeserializer : JsonDeserializer<FriendsData> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): FriendsData {
        val content = json?.asJsonObject?.get("friends")?.asJsonArray
        val users = mutableListOf<UserData>()
        content?.forEach {
            val user = gson.fromJson(it, UserData::class.java)
            user.let(users::add)
        }
        return FriendsData(users)
    }
}