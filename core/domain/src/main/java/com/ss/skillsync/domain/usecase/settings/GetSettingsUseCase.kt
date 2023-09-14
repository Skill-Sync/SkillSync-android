package com.ss.skillsync.domain.usecase.settings

import com.ss.skillsync.domain.repository.SettingsRepository
import com.ss.skillsync.model.Settings
import javax.inject.Inject

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 14/09/2023
 */
class GetSettingsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    suspend operator fun invoke(): Settings {
        return Settings(
            settingsRepository.isDarkMode(),
            settingsRepository.isNotificationsEnabled(),
        )
    }
}