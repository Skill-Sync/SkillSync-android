package com.ss.skillsync.domain.repository

import com.ss.skillsync.model.SessionMakingEvent
import com.ss.skillsync.model.Skill
import com.ss.skillsync.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/14/2023.
 */
interface SessionMakingRepository {

    val eventsFlow: Flow<SessionMakingEvent>

    suspend fun connect(user: User): Result<Unit>

    suspend fun startSearching(
        user: User,
        skill: Skill
    )
    suspend fun stopSearching()

    suspend fun approveMatch()

    suspend fun rejectMatch()

    suspend fun disconnect()
}