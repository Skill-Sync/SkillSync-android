package com.ss.skillsync.meeting.impl

import com.ss.skillsync.meeting.api.MeetingManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/9/2023.
 */
@Module
@InstallIn(ActivityComponent::class)
interface MeetingModule {

    @Binds
    @ActivityScoped
    fun bindMeetingManager(impl: MeetingManagerImpl): MeetingManager
}