package com.stathis.domain.usecases.search

import com.stathis.common.util.toNotNull
import com.stathis.domain.repository.QueriesRepository
import javax.inject.Inject

class SaveQueryToLocalDbUseCase @Inject constructor(
    private val repo: QueriesRepository
) {

    suspend fun invoke(vararg args: Any?) {
        val query = (args.getOrNull(0) as? String?).toNotNull()
        repo.insertNewUserQuery(query)
    }
}
