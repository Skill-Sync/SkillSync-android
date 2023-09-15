package com.ss.skillsync.data.source.remote.model.session

import com.google.gson.annotations.SerializedName

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/11/2023.
 */
data class SessionData(
    @SerializedName("_id")
    val sessionId: String?,
    val userId: String?,
    val name: String?,
    val scheduledDate: String?,
    val status: String?,
    val photo: String?,
    val skill: String?,
)
