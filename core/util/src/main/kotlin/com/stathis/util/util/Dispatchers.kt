package com.stathis.util.util

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Interface that provides different types of Dispatchers used for coroutine execution.
 *
 * This allows the separation of threading concerns and provides the ability to inject
 * custom dispatchers during testing or in different runtime environments.
 */
interface Dispatchers {

    /**
     * Returns a [CoroutineDispatcher] that is optimized for tasks that need to be run on the main thread.
     * Typically used for UI-related work in Android.
     *
     * @return [kotlinx.coroutines.Dispatchers.Main]
     */
    fun main(): CoroutineDispatcher

    /**
     * Returns a [CoroutineDispatcher] that is optimized for CPU-intensive work.
     * Use this dispatcher for tasks like sorting large lists or performing complex calculations.
     *
     * @return [kotlinx.coroutines.Dispatchers.Default]
     */
    fun default(): CoroutineDispatcher

    /**
     * Returns a [CoroutineDispatcher] that is optimized for I/O-bound work.
     * Ideal for tasks such as reading from or writing to files, making network calls, or accessing databases.
     *
     * @return [kotlinx.coroutines.Dispatchers.IO]
     */
    fun io(): CoroutineDispatcher
}
