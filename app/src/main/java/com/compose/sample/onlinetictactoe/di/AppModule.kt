package com.compose.sample.onlinetictactoe.di

import com.compose.sample.onlinetictactoe.data.KtorRealTimeMessageClient
import com.compose.sample.onlinetictactoe.data.RealTimeMessageClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(Logging)
            install(WebSockets)
        }
    }

    @Provides
    @Singleton
    fun provideRealTimeMessageClient(client: HttpClient): RealTimeMessageClient {
        return KtorRealTimeMessageClient(client)
    }
}