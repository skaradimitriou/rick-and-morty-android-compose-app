package com.stathis.util.util

import kotlinx.coroutines.CoroutineDispatcher

class DispatchersImpl : Dispatchers {

    override fun main(): CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Main

    override fun default(): CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Default

    override fun io(): CoroutineDispatcher = kotlinx.coroutines.Dispatchers.IO
}
