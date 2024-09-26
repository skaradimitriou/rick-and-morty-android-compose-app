package com.stathis.data.repository

import com.stathis.data.mapper.characters.CharacterMapper
import com.stathis.model.characters.CharacterResponse
import com.stathis.network.service.RickAndMortyApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: RickAndMortyApi
) : CharactersRepository {

    override suspend fun getAllCharacters(): Flow<List<CharacterResponse>> = flow {
        val data = CharacterMapper.toDomainModel(
            dto = remoteDataSource.getAllCharacters()?.body()
        )
        //FIXME: improve that so that it fetches and maps the data into a network result
        emit(data.results)
    }
}