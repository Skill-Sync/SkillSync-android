package com.ss.skillsync.data.repository

import com.ss.skillsync.data.mapper.toDomain
import com.ss.skillsync.data.source.remote.session_making.SessionMakingRemoteSource
import com.ss.skillsync.domain.repository.SessionMakingRepository
import com.ss.skillsync.model.MatchResult
import com.ss.skillsync.model.SessionMakingEvent
import com.ss.skillsync.model.Skill
import com.ss.skillsync.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/15/2023.
 */
class SessionMakingRepositoryImpl @Inject constructor(
    private val sessionMakingRemoteSource: SessionMakingRemoteSource,
) : SessionMakingRepository {

    private val _eventsFlow = MutableStateFlow<SessionMakingEvent>(SessionMakingEvent.Idle)
    override val eventsFlow: Flow<SessionMakingEvent> = _eventsFlow.asStateFlow()
        .onEach {
            if (it is SessionMakingEvent.MatchFound) {
                matchFoundEvent = it
            }
        }

    private var matchFoundEvent: SessionMakingEvent.MatchFound? = null
    private var currentUser: User? = null
    private var selectedSkill: Skill? = null


    override suspend fun connect(user: User): Result<Unit> {
        val result = sessionMakingRemoteSource.connect()
        currentUser = user
        sessionMakingRemoteSource.listenForEvents(user.id) { event ->
            when (event) {
                is SessionMakingRemoteSource.Event.MatchFound -> {
                    val matchedUser = event.matchedUser.toDomain()
                    val matchResult = MatchResult(
                        matchedUser.id,
                        matchedUser.name,
                        profilePictureUrl = matchedUser.profilePictureUrl,
                        matchedSkill = selectedSkill!!
                    )
                    _eventsFlow.value = SessionMakingEvent.MatchFound(currentUser!!, matchResult)
                }

                is SessionMakingRemoteSource.Event.ServerApproval -> {
                    _eventsFlow.value = SessionMakingEvent.MatchApproved(event.authToken)
                }

                is SessionMakingRemoteSource.Event.ServerRejection -> {
                    matchFoundEvent = null
                    _eventsFlow.value = SessionMakingEvent.MatchRejected
                }
            }
        }
        return result
    }

    override suspend fun startSearching(user: User, skill: Skill) {
        currentUser = user
        selectedSkill = skill
        sessionMakingRemoteSource.sendMessage(
            SessionMakingRemoteSource.Message.StartSearching(
                user.id,
                skill.name
            )
        ).onFailure {
            emitError()
        }
    }

    override suspend fun stopSearching() {
        val currentUserId = currentUser?.id ?: kotlin.run { emitError(); return }
        sessionMakingRemoteSource.sendMessage(
            SessionMakingRemoteSource.Message.CancelSearch(
                currentUserId
            )
        )
        sessionMakingRemoteSource.disconnect()
            .onFailure {
                emitError()
            }
    }

    override suspend fun approveMatch() {
        val currentUserId = currentUser?.id ?: kotlin.run { emitError(); return }
        val matchedUserId =
            matchFoundEvent?.matchResult?.userId ?: kotlin.run { emitError(); return }

        sessionMakingRemoteSource.sendMessage(
            SessionMakingRemoteSource.Message.Approve(
                currentUserId,
                matchedUserId
            )
        )
            .onFailure {
                _eventsFlow.value = SessionMakingEvent.UnknownError
            }
    }

    override suspend fun rejectMatch() {
        val currentUserId = currentUser?.id ?: kotlin.run { emitError(); return }
        val matchedUserId =
            matchFoundEvent?.matchResult?.userId ?: kotlin.run { emitError(); return }

        sessionMakingRemoteSource.sendMessage(
            SessionMakingRemoteSource.Message.Reject(
                currentUserId,
                matchedUserId
            )
        )
            .onFailure {
                _eventsFlow.value = SessionMakingEvent.UnknownError
            }
    }

    override suspend fun disconnect() {
        sessionMakingRemoteSource.sendMessage(
            SessionMakingRemoteSource.Message.CancelSearch(
                currentUser!!.id
            )
        )
        sessionMakingRemoteSource.disconnect()
            .onFailure {
                emitError()
            }
    }

    private fun emitError() {
        _eventsFlow.value = SessionMakingEvent.UnknownError
    }
}