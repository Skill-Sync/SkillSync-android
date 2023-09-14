package com.ss.skillsync

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 8/20/2023.
 */
class ReportingTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.WARN || priority == Log.ERROR) {
            recordException(priority, tag, message, t)
        }
    }

    private fun recordException(
        priority: Int,
        tag: String?,
        message: String,
        t: Throwable?,
    ) = with(FirebaseCrashlytics.getInstance()) {
        setCustomKey("priority", priority)
        setCustomKey("tag", tag ?: "")
        setCustomKey("message", message)
        log("E/TAG: $message")
        recordException(t ?: Exception(message))
    }
}
