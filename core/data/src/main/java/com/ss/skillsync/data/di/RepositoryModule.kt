package com.ss.skillsync.data.di

import com.ss.skillsync.data.repository.MentorRepositoryImpl
import com.ss.skillsync.data.repository.SessionRepositoryImpl
import com.ss.skillsync.data.repository.SkillRepositoryImpl
import com.ss.skillsync.data.repository.UserRepositoryImpl
import com.ss.skillsync.domain.repository.MentorRepository
import com.ss.skillsync.domain.repository.SessionRepository
import com.ss.skillsync.domain.repository.SkillRepository
import com.ss.skillsync.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 07/09/2023
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl,
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindSkillRepository(
        skillRepositoryImpl: SkillRepositoryImpl,
    ): SkillRepository

    @Binds
    @Singleton
    abstract fun bindMentorRepository(
        mentorRepositoryImpl: MentorRepositoryImpl,
    ): MentorRepository

    @Binds
    @Singleton
    abstract fun bindSessionRepository(
        sessionRepositoryImpl: SessionRepositoryImpl,
    ): SessionRepository
}
