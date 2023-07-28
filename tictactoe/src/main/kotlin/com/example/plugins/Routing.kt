package com.example.plugins

import com.example.model.TicTacToeGame
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import socket

fun Application.configureRouting(game: TicTacToeGame) {
    routing {
        socket(game)
    }
}
