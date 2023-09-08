package com.ss.skillsync.data.di

import com.ss.skillsync.data.BuildConfig
import com.ss.skillsync.data.interceptor.AuthInterceptor
import com.ss.skillsync.data.retrofit.UserApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
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
    fun provideOkHttpClient(): OkHttpClient = if (BuildConfig.DEBUG) {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().
                setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .addInterceptor(AuthInterceptor())
            .build()
    } else {
        OkHttpClient
            .Builder()
            .addInterceptor(AuthInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("" /* TODO: Add Tha BaseURL */)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideUserApiService(retrofit: Retrofit): UserApiService =
        retrofit.create(UserApiService::class.java)
}
