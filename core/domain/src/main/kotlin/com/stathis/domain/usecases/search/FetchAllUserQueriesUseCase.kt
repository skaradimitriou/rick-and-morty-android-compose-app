package com.stathis.domain.usecases.search

import com.stathis.domain.repository.QueriesRepository
import com.stathis.domain.usecases.BaseUseCase
import com.stathis.model.search.Query
import javax.inject.Inject

class FetchAllUserQueriesUseCase @Inject constructor(
    private val repo: QueriesRepository
) : BaseUseCase<List<Query>> {

    override suspend fun invoke(vararg args: Any?) = repo.fetchAllUserQueries()
}
