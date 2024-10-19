package com.stathis.domain.repository

import com.stathis.model.search.Query
import kotlinx.coroutines.flow.Flow

interface QueriesRepository {

    suspend fun fetchAllUserQueries(): Flow<List<Query>>

    suspend fun insertNewUserQuery(name: String)
}
