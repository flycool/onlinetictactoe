package com.compose.sample.onlinetictactoe.data

import kotlinx.coroutines.flow.Flow

interface RealTimeMessageClient {
    fun getGameStateStream(): Flow<GameState>
    suspend fun sendAction(action: MakeTurn)
    suspend fun close()
}