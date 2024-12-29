package com.stathis.data.repository

import com.stathis.database.db.queries.QueriesLocalDatabase
import com.stathis.database.db.queries.QueryEntity
import com.stathis.database.util.toQuery
import com.stathis.domain.repository.QueriesRepository
import com.stathis.model.search.Query
import com.stathis.util.util.toListOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class QueriesRepositoryImpl(
    private val localDataSource: QueriesLocalDatabase
) : QueriesRepository {

    override suspend fun fetchAllUserQueries(): Flow<List<Query>> = localDataSource.dao()
        .getAllQueries()
        .map { results ->
            results.toListOf { it.toQuery() }
        }

    override suspend fun insertNewUserQuery(name: String) {
        localDataSource.dao()
            .getQueryByName(name)
            .collect { result ->
                if (result.isEmpty()) {
                    val newQuery = QueryEntity(name = name)
                    localDataSource.dao().insert(newQuery)
                }
            }
    }
}
