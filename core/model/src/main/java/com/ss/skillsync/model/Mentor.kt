package com.ss.skillsync.model

import kotlinx.serialization.Serializable

@Serializable
data class Mentor(
    val id: String = "",
    val name: String = "",
    val pictureUrl: String = "",
    val field: String = "",
    val about: String = "",
    val email: String = "",
)
