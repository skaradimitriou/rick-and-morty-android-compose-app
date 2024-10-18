package com.stathis.domain.usecases.search

import com.stathis.domain.repository.QueriesRepository
import javax.inject.Inject

class SaveQueryToLocalDbUseCase @Inject constructor(
    private val repo: QueriesRepository
) {

}
