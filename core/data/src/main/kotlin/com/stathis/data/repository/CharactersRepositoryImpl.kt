package com.stathis.data.repository

import com.stathis.data.mapper.characters.CharacterMapper
import com.stathis.database.db.CharactersLocalDatabase
import com.stathis.database.util.toCharacter
import com.stathis.database.util.toEntity
import com.stathis.domain.repository.CharactersRepository
import com.stathis.model.characters.CharacterResponse
import com.stathis.network.service.RickAndMortyApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: RickAndMortyApi,
    private val localDataSource: CharactersLocalDatabase
) : CharactersRepository {

    override suspend fun getAllCharacters(): Flow<List<CharacterResponse>> = flow {
        //FIXME: Temp solution. To be moved later on.

        val data = CharacterMapper.toDomainModel(
            dto = remoteDataSource.getAllCharacters()?.body()
        )

        with(localDataSource.dao()) {
            deleteAll()
            insertAll(
                data.results.map { it.toEntity() }
            )

            getAllCharacters().map { dbResults ->
                dbResults.map { it.toCharacter() }
            }.collect {
                emit(it)
            }
        }
    }

    override suspend fun getCharacterById(id: Int): Flow<CharacterResponse> {
        return localDataSource.dao().getCharacterById(id).map {
            it?.toCharacter()
        }.filterNotNull()
    }
}
