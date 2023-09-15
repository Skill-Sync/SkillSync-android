package com.ss.skillsync.data.source.remote.friends

import com.ss.skillsync.data.source.remote.model.UsersData
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/15/2023.
 */
interface FriendsApiService {

    @GET("friends")
    suspend fun getAllFriends(): Response<UsersData>
}