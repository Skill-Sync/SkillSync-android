package com.ss.skillsync.data.source.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.ss.skillsync.data.source.remote.model.session.SessionData
import com.ss.skillsync.data.source.remote.model.session.SessionsResponse
import java.lang.reflect.Type

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/11/2023.
 */
class SessionsResponseDeserializer : JsonDeserializer<SessionsResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): SessionsResponse {
        val content = json?.asJsonObject?.get("data")?.asJsonArray
        val sessions = mutableListOf<SessionData>()
        content?.forEach {
            val session = SessionDataDeserializer().deserialize(it, SessionData::class.java, context)
            session.let(sessions::add)
        }
        return SessionsResponse(sessions)
    }
}

class SessionDataDeserializer : JsonDeserializer<SessionData> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): SessionData {
        val content = json?.asJsonObject
        val sessionId = json?.asJsonObject?.get("_id")?.asString
        val mentor = json?.asJsonObject?.get("mentor")?.asJsonObject

        return SessionData(
            sessionId = sessionId,
            userId = mentor?.get("_id")?.asString,
            name = mentor?.get("name")?.asString,
            scheduledDate = content?.get("scheduledDate")?.asString,
            status = content?.get("status")?.asString,
            photo = mentor?.get("photo")?.asString,
            skill = mentor?.get("skill")?.asString,
        )
    }
}
