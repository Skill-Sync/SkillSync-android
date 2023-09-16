package com.ss.skillsync.data.source.remote.session_making

import com.google.gson.Gson
import com.ss.skillsync.data.source.remote.model.auth.UserData
import com.ss.skillsync.data.source.remote.session_making.response.MatchFoundResponse
import com.ss.skillsync.data.source.remote.session_making.response.ServerApprovalResponse
import com.tfowl.socketio.connectAwait
import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONObject
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/15/2023.
 */
class SessionMakingRemoteSource @Inject constructor() {

    companion object {
        private const val BASE_URL = "https://skill-sync-backup.onrender.com/"
        private const val TAG = "SessionMakingRemoteSource"
    }

    private var socket: Socket? = null
    private var socketId: String? = null
    private val gson = Gson()

    suspend fun connect(): Result<Unit> {
        if (socket?.connected() == true) {
            return Result.success(Unit)
        }

        return kotlin.runCatching {
            socket = IO.socket(BASE_URL).connectAwait()
            socketId = socket?.id()
        }.onFailure {
            com.timers.stopwatch.core.log.error(TAG, it)
        }
    }

    fun disconnect(): Result<Unit> {
        return kotlin.runCatching {
            socket?.disconnect()
            Unit
        }.onFailure {
            com.timers.stopwatch.core.log.error(TAG, it)
        }
    }

    fun sendMessage(message: Message) = kotlin.runCatching {
        val messageJson = message.toJson()
        messageJson.put("userSocketId", socketId)
        println(messageJson)
        socket?.emit(message.name, messageJson)
    }.onFailure {
        com.timers.stopwatch.core.log.error(TAG, it)
    }

    fun listenForEvents(
        currentUserId: String,
        onEvent: (Event) -> Unit,
    ) {
        socket?.on("match-found") { args ->
            val eventArgs = args[0].toString()
            val matchFoundResponse = gson.fromJson(eventArgs, MatchFoundResponse::class.java)
            onEvent(Event.MatchFound(matchFoundResponse.getMatchUser(currentUserId)))
        }

        socket?.on("server-approval") { args ->
            val eventArgs = args[0].toString()
            val matchFoundResponse = gson.fromJson(eventArgs, ServerApprovalResponse::class.java)
            onEvent(Event.ServerApproval(matchFoundResponse.authToken))
        }

        socket?.on("server-rejection") {
            onEvent(Event.ServerRejection)
        }
    }

    sealed class Message(val name: String) {
        data class StartSearching(
            val userId: String,
            val wantedInnerSkill: String,
        ) : Message("start-searching") {
            override fun toJson(): JSONObject {
                val jsonObject = JSONObject()
                jsonObject.put("userId", userId)
                jsonObject.put("wantedInnerSkill", wantedInnerSkill)
                return jsonObject
            }
        }

        data class Approve(
            val userId: String,
            val MatchedUserId: String,
        ) : Message("client-approval") {
            override fun toJson(): JSONObject {
                val jsonObject = JSONObject()
                jsonObject.put("userId", userId)
                jsonObject.put("MatchedUserId", MatchedUserId)
                return jsonObject
            }
        }

        data class Reject(
            val userId: String,
            val MatchedUserId: String,
        ) : Message("client-rejection") {
            override fun toJson(): JSONObject {
                val jsonObject = JSONObject()
                jsonObject.put("userId", userId)
                jsonObject.put("MatchedUserId", MatchedUserId)
                return jsonObject
            }
        }

        data class CancelSearch(
            val userId: String,
        ) : Message("cancel-search") {
            override fun toJson(): JSONObject {
                val jsonObject = JSONObject()
                jsonObject.put("userId", userId)
                return jsonObject
            }
        }

        abstract fun toJson(): JSONObject
    }

    sealed class Event {
        data class MatchFound(val matchedUser: UserData) : Event()
        data class ServerApproval(val authToken: String) : Event()

        data object ServerRejection : Event()
    }
}