package com.stathis.domain.usecases.search

import com.stathis.domain.repository.QueriesRepository
import com.stathis.util.util.toNotNull

class SaveQueryToLocalDbUseCase(
    private val repo: QueriesRepository
) {

    suspend fun invoke(vararg args: Any?) {
        val query = (args.getOrNull(0) as? String?).toNotNull()
        repo.insertNewUserQuery(query)
    }
}
