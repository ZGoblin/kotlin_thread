package com.example.kotlin_thread

import java.util.concurrent.Executors
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

var counter = 0
val rwLocker = ReentrantReadWriteLock()

fun increaseCounter() {
    while (true) {
        Thread.sleep(10)
        rwLocker.write {
            ++counter
        }
    }
}

fun printCounter() {
    while (true) {
        Thread.sleep(1000)
        rwLocker.read {
            println("Counter $counter")
        }
    }
}

fun main() {
    val threadPool = Executors.newFixedThreadPool(5)

    threadPool.execute(::increaseCounter)
    threadPool.execute(::increaseCounter)
    threadPool.execute(::increaseCounter)
    threadPool.execute(::increaseCounter)
    threadPool.execute(::printCounter)
}