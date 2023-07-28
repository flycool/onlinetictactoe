package com.compose.sample.onlinetictactoe

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking




fun main() = runBlocking{


    val f = flow {

        println("before emit===")

        emit(1)
        emit(2)
        emit(3)
    }

    f.collect {
        println(it)
    }

    f.collect {
        println("22==$it")
    }
}