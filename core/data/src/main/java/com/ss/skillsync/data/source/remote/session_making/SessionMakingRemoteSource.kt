package com.ss.skillsync.data.source.remote.session_making

import com.google.gson.Gson
import com.ss.skillsync.data.source.remote.model.auth.UserData
import com.ss.skillsync.data.source.remote.session_making.response.MatchFoundResponse
import com.ss.skillsync.data.source.remote.session_making.response.ServerApprovalResponse
import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONObject
import javax.inject.Inject

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/15/2023.
 */
class SessionMakingRemoteSource @Inject constructor() {

    companion object {
        private const val BASE_URL = "https://skill-sync.onrender.com/"
    }

    private var socket: Socket? = null
    private var socketId: String? = null

    fun connect(): Result<Unit> {
        if (socket != null) {
            return Result.success(Unit)
        }

        return kotlin.runCatching {
            socket = IO.socket(BASE_URL).connect()
            socketId = socket?.id()
        }
    }

    fun disconnect(): Result<Unit> {
        return kotlin.runCatching {
            socket?.disconnect()
        }
    }

    fun sendMessage(message: Message) = kotlin.runCatching {
        val messageJson = JSONObject(message.toString())
        messageJson.put("userSocketId", socketId)
        socket?.emit(message.name, messageJson)
    }

    fun listenForEvents(
        currentUserId: String,
        onEvent: (Event) -> Unit,
    ) {
        val gson = Gson()
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
        ) : Message("start-searching")

        data class Approve(
            val userId: String,
            val MatchedUserId: String,
        ) : Message("client-approval")

        data class Reject(
            val userId: String,
            val MatchedUserId: String,
        ) : Message("client-rejection")
    }

    sealed class Event {
        data class MatchFound(val matchedUser: UserData) : Event()
        data class ServerApproval(val authToken: String) : Event()

        data object ServerRejection : Event()
    }
}