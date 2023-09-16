package com.ss.skillsync.data.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.ss.skillsync.data.BuildConfig
import com.ss.skillsync.data.preferences.SettingsPreferences
import com.ss.skillsync.data.preferences.UserPreferences
import com.ss.skillsync.data.source.deserializer.FriendsDataDeserializer
import com.ss.skillsync.data.source.deserializer.SessionsResponseDeserializer
import com.ss.skillsync.data.source.deserializer.UserDataDeserializer
import com.ss.skillsync.data.source.deserializer.UsersDataDeserializer
import com.ss.skillsync.data.source.remote.friends.FriendsApiService
import com.ss.skillsync.data.source.remote.interceptor.AuthInterceptor
import com.ss.skillsync.data.source.remote.mentors.MentorApiService
import com.ss.skillsync.data.source.remote.model.FriendsData
import com.ss.skillsync.data.source.remote.model.UsersData
import com.ss.skillsync.data.source.remote.model.auth.UserData
import com.ss.skillsync.data.source.remote.model.session.SessionsResponse
import com.ss.skillsync.data.source.remote.profile.SkillApiService
import com.ss.skillsync.data.source.remote.session.SessionApiService
import com.ss.skillsync.data.source.remote.user.UserApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 08/09/2023
 */

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        val timeout = 30_000L
        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .callTimeout(timeout, TimeUnit.MILLISECONDS)
            .connectTimeout(timeout, TimeUnit.MILLISECONDS)
            .readTimeout(timeout, TimeUnit.MILLISECONDS)
            .writeTimeout(timeout, TimeUnit.MILLISECONDS)

        if (BuildConfig.DEBUG) {
            client.addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY),
            )
        }
        return client.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = kotlin.run {
        val gson = GsonBuilder()
            .registerTypeAdapter(UserData::class.java, UserDataDeserializer())
            .registerTypeAdapter(UsersData::class.java, UsersDataDeserializer())
            .registerTypeAdapter(SessionsResponse::class.java, SessionsResponseDeserializer())
            .registerTypeAdapter(FriendsData::class.java, FriendsDataDeserializer())
            .create()
        val url = "https://skill-sync.onrender.com/"
        val postfix = "api/v1/"
        Retrofit.Builder()
            .baseUrl(url + postfix)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideUserApiService(retrofit: Retrofit): UserApiService =
        retrofit.create(UserApiService::class.java)

    @Provides
    @Singleton
    fun provideSkillApiService(retrofit: Retrofit): SkillApiService =
        retrofit.create(SkillApiService::class.java)

    @Provides
    @Singleton
    fun provideSessionApiService(retrofit: Retrofit): SessionApiService =
        retrofit.create(SessionApiService::class.java)

    @Provides
    @Singleton
    fun provideMentorApiService(retrofit: Retrofit): MentorApiService =
        retrofit.create(MentorApiService::class.java)

    @Provides
    @Singleton
    fun provideFriendsApiService(retrofit: Retrofit): FriendsApiService =
        retrofit.create(FriendsApiService::class.java)

    @Provides
    @Singleton
    fun provideUserPreferences(
        @ApplicationContext context: Context,
    ): UserPreferences = UserPreferences(context)

    @Provides
    @Singleton
    fun provideSettingsPreferences(
        @ApplicationContext context: Context,
    ): SettingsPreferences = SettingsPreferences(context)
}
