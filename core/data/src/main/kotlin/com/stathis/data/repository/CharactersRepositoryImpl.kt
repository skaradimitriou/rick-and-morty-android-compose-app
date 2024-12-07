package com.stathis.data.repository

import com.stathis.common.util.toNotNull
import com.stathis.data.mapper.characters.CharacterListMapper
import com.stathis.data.mapper.characters.CharacterMapper
import com.stathis.data.mapper.characters.CharacterResponseMapper
import com.stathis.data.util.mapToDomainResult
import com.stathis.database.db.characters.CharactersLocalDatabase
import com.stathis.database.util.toCharacter
import com.stathis.database.util.toEntity
import com.stathis.domain.repository.CharactersRepository
import com.stathis.model.Result
import com.stathis.model.characters.CharacterResponse
import com.stathis.network.service.RickAndMortyApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal class CharactersRepositoryImpl(
    private val remoteDataSource: RickAndMortyApi,
    private val localDataSource: CharactersLocalDatabase
) : CharactersRepository {

    override suspend fun getAllCharacters(): Flow<Result<List<CharacterResponse>>> = flow {
        val result = mapToDomainResult(
            networkCall = { remoteDataSource.getAllCharacters() },
            mapping = { CharacterMapper.toDomainModel(it) }
        )

        if (result is Result.Success) {
            with(localDataSource.dao()) {
                deleteAll()
                insertAll(
                    result.data.results.map { it.toEntity() }
                )

                getAllCharacters().map { dbResults ->
                    dbResults.map { it.toCharacter() }
                }.collect {
                    emit(Result.Success(it))
                }
            }
        }
    }

    override suspend fun getCharacterById(id: Int): Flow<Result<CharacterResponse>> = flow {
        localDataSource.dao().getCharacterById(id).catch {
            emit(Result.Error(errorCode = 404, message = it.localizedMessage.toNotNull()))
        }.collect { characterFromLocalDataSource ->
            characterFromLocalDataSource?.let {
                emit(Result.Success(it.toCharacter()))
            } ?: run {
                getCharacterByIdFromRemoteDataSource(id).collect { characterFromRemote ->
                    emit(characterFromRemote)
                }
            }
        }
    }

    override suspend fun getCharacterByName(name: String): Flow<Result<List<CharacterResponse>>> = flow {
        val result = mapToDomainResult(
            networkCall = { remoteDataSource.getCharacterByName(name) },
            mapping = { CharacterMapper.toDomainModel(it).results }
        )

        emit(result)
    }

    private fun getCharacterByIdFromRemoteDataSource(id: Int): Flow<Result<CharacterResponse>> = flow {
        val result = mapToDomainResult(
            networkCall = { remoteDataSource.getCharacterById(id) },
            mapping = { CharacterResponseMapper.toDomainModel(it) }
        )

        emit(result)
    }

    override suspend fun getMultipleCharacterById(ids: List<String>): Flow<Result<List<CharacterResponse>>> = flow {
        val result = mapToDomainResult(
            networkCall = { remoteDataSource.getMultipleCharactersById(ids) },
            mapping = { CharacterListMapper.toDomainModel(it) }
        )

        emit(result)
    }
}
