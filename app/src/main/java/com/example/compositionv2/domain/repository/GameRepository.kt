package com.example.compositionv2.domain.repository

import com.example.compositionv2.domain.entity.GameSettings
import com.example.compositionv2.domain.entity.Level
import com.example.compositionv2.domain.entity.Question

interface GameRepository {

    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings
}