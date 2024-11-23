package com.stathis.data.repository

import com.stathis.common.util.toListOf
import com.stathis.database.db.queries.QueriesLocalDatabase
import com.stathis.database.db.queries.QueryEntity
import com.stathis.database.util.toQuery
import com.stathis.domain.repository.QueriesRepository
import com.stathis.model.search.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class QueriesRepositoryImpl @Inject constructor(
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
