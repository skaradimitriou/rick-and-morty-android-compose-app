package com.stathis.domain.usecases

import kotlinx.coroutines.flow.Flow

interface BaseUseCase<T> {
    suspend operator fun invoke(vararg: Any): Flow<T>
}