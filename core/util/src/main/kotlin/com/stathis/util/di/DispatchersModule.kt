package com.stathis.util.di

import com.stathis.util.util.Dispatchers
import com.stathis.util.util.DispatchersImpl
import org.koin.dsl.module

val dispatchersModule = module {
    single<Dispatchers> { DispatchersImpl() }
}
