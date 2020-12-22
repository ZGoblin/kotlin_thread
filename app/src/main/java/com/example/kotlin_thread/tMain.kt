package com.example.kotlin_thread

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

var atomicInteger = AtomicInteger(0)

fun increaseCounter() {
    while (true) {
        Thread.sleep(10)
        atomicInteger.incrementAndGet()
    }
}

fun printCounter() {
    while (true) {
        Thread.sleep(1000)
        println("Counter ${atomicInteger.get()}")
    }
}

fun main() {
    val threadPool = Executors.newFixedThreadPool(5)

    var callables = mutableListOf<Callable<Unit>>()
    callables.add(::increaseCounter)
    callables.add(::increaseCounter)
    callables.add(::increaseCounter)
    callables.add(::increaseCounter)
    callables.add(::printCounter)

    threadPool.invokeAll(callables)
}