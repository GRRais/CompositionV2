package com.example.compositionv2.domain.usecases

import com.example.compositionv2.domain.entity.GameSettings
import com.example.compositionv2.domain.entity.Level
import com.example.compositionv2.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {
    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}