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

    suspend fun startSearching(
        user: User,
        skill: Skill
    ): Result<Unit>

    suspend fun stopSearching(): Result<Unit>

    suspend fun approveMatch(): Result<Unit>

    suspend fun rejectMatch(): Result<Unit>

    suspend fun disconnect(): Result<Unit>
}