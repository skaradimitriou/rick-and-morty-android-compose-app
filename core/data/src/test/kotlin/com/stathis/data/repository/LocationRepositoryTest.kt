package com.stathis.data.repository

import com.stathis.model.Result
import com.stathis.model.location.Location
import com.stathis.network.datasource.LocationsRemoteDataSource
import com.stathis.network.model.NetworkResult
import com.stathis.network.model.location.LocationDto
import com.stathis.network.model.location.LocationWrapperDto
import com.stathis.util.errors.NetworkError
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LocationRepositoryTest {

    private val api: LocationsRemoteDataSource = mockk()
    private val dispatcher = StandardTestDispatcher()

    private lateinit var repository: LocationRepositoryImpl

    companion object {

        private const val ERROR_CODE = 400
        private const val DUMMY_LOCATION_ID = 1
        private val DUMMY_MULTIPLE_LOCATION_IDS = listOf<String>("1", "2", "3")
        private const val DUMMY_LOCATION_NAME = "Earth"
    }

    @Before
    fun setUp() {
        repository = LocationRepositoryImpl(remoteDataSource = api)
    }

    @Test
    fun `given valid location id, when calling getLocationById method, then return successful mapped domain result`() =
        runTest(dispatcher) {
            val response: NetworkResult.Success<LocationDto?> = NetworkResult.Success(
                LocationDto(
                    id = 1,
                    name = "Earth",
                    type = "Location type",
                    dimension = "Dimension",
                    residents = listOf("abc/1", "abc/2", "abc/3"),
                    url = "my.awesome.url",
                    created = "XX-XX-2024"
                )
            )

            coEvery { api.fetchLocationById(DUMMY_LOCATION_ID) } returns response

            val expected: Location = Location(
                id = 1,
                name = "Earth",
                type = "Location type",
                dimension = "Dimension",
                residents = listOf("1", "2", "3"),
                url = "my.awesome.url",
                created = "XX-XX-2024"
            )

            repository.getLocationById(DUMMY_LOCATION_ID).collect { result ->
                coEvery { api.fetchLocationById(DUMMY_LOCATION_ID) }

                assertTrue(result is Result.Success)
                assertEquals(result.data, expected)
            }
        }

    @Test
    fun `given valid location id, when calling getLocationById method, then return failure mapped domain result`() =
        runTest(dispatcher) {
            val response: NetworkResult.Error<LocationDto?> = NetworkResult.Error<LocationDto?>(
                ERROR_CODE,
                "Something went wrong"
            )

            coEvery { api.fetchLocationById(DUMMY_LOCATION_ID) } returns response

            repository.getLocationById(DUMMY_LOCATION_ID).collect { result ->
                coEvery { api.fetchLocationById(DUMMY_LOCATION_ID) }

                assertTrue(result is Result.Error && result.exception is NetworkError.Generic)
                with(result.exception) {
                    assertEquals(ERROR_CODE, (this as NetworkError.Generic).errorCode)
                    assertTrue(message.toString().isNotEmpty())
                }
            }
        }

    @Test
    fun `given multiple valid location ids, when calling getMultipleLocationsById method, then return successful mapped domain result`() =
        runTest(dispatcher) {
            val response: NetworkResult.Success<List<LocationDto>?> = NetworkResult.Success(
                listOf(
                    LocationDto(
                        id = 1,
                        name = "Earth",
                        type = "Location type",
                        dimension = "Dimension",
                        residents = listOf("abc/1", "abc/2", "abc/3"),
                        url = "my.awesome.url",
                        created = "XX-XX-2024"
                    ),
                    LocationDto(
                        id = 2,
                        name = "Mars",
                        type = "Location type",
                        dimension = "Dimension",
                        residents = listOf("abc/1", "abc/2", "abc/3"),
                        url = "my.awesome.url",
                        created = "XX-XX-2024"
                    ),
                    LocationDto(
                        id = 3,
                        name = "Venus",
                        type = "Location type",
                        dimension = "Dimension",
                        residents = listOf("abc/1", "abc/2", "abc/3"),
                        url = "my.awesome.url",
                        created = "XX-XX-2024"
                    )
                )
            )

            coEvery { api.fetchMultipleLocationsById(DUMMY_MULTIPLE_LOCATION_IDS) } returns response

            val expected: List<Location> = listOf(
                Location(
                    id = 1,
                    name = "Earth",
                    type = "Location type",
                    dimension = "Dimension",
                    residents = listOf("1", "2", "3"),
                    url = "my.awesome.url",
                    created = "XX-XX-2024"
                ),
                Location(
                    id = 2,
                    name = "Mars",
                    type = "Location type",
                    dimension = "Dimension",
                    residents = listOf("1", "2", "3"),
                    url = "my.awesome.url",
                    created = "XX-XX-2024"
                ),
                Location(
                    id = 3,
                    name = "Venus",
                    type = "Location type",
                    dimension = "Dimension",
                    residents = listOf("1", "2", "3"),
                    url = "my.awesome.url",
                    created = "XX-XX-2024"
                )
            )

            repository.getMultipleLocationsById(DUMMY_MULTIPLE_LOCATION_IDS).collect { result ->
                coEvery { api.fetchMultipleLocationsById(DUMMY_MULTIPLE_LOCATION_IDS) }

                assertTrue(result is Result.Success)

                result.data.zip(expected) { domainModel, expected ->
                    assertEquals(domainModel.id, expected.id)
                    assertEquals(domainModel.name, expected.name)
                    assertEquals(domainModel.type, expected.type)
                    assertEquals(domainModel.dimension, expected.dimension)
                    assertEquals(domainModel.residents, expected.residents)
                    assertEquals(domainModel.url, expected.url)
                    assertEquals(domainModel.created, expected.created)
                }
            }
        }

    @Test
    fun `given multiple valid location ids, when calling getMultipleLocationsById method, then return failure mapped domain result`() =
        runTest(dispatcher) {
            val response: NetworkResult.Error<List<LocationDto>?> = NetworkResult.Error<List<LocationDto>?>(
                ERROR_CODE,
                "Something went wrong"
            )

            coEvery { api.fetchMultipleLocationsById(DUMMY_MULTIPLE_LOCATION_IDS) } returns response

            repository.getMultipleLocationsById(DUMMY_MULTIPLE_LOCATION_IDS).collect { result ->
                coEvery { api.fetchMultipleLocationsById(DUMMY_MULTIPLE_LOCATION_IDS) }

                assertTrue(result is Result.Error && result.exception is NetworkError.Generic)
                with(result.exception) {
                    assertEquals(ERROR_CODE, (this as NetworkError.Generic).errorCode)
                    assertTrue(message.toString().isNotEmpty())
                }
            }
        }

    @Test
    fun `given valid location name, when calling getLocationByName method, then return successful mapped domain result`() =
        runTest(dispatcher) {
            val response: NetworkResult.Success<LocationWrapperDto?> = NetworkResult.Success(
                LocationWrapperDto(
                    results = listOf(
                        LocationDto(
                            id = 1,
                            name = "Earth",
                            type = "Location type",
                            dimension = "Dimension",
                            residents = listOf("abc/1", "abc/2", "abc/3"),
                            url = "my.awesome.url",
                            created = "XX-XX-2024"
                        )
                    )
                )
            )

            coEvery { api.fetchLocationByName(DUMMY_LOCATION_NAME) } returns response

            val expected: Location = Location(
                id = 1,
                name = "Earth",
                type = "Location type",
                dimension = "Dimension",
                residents = listOf("1", "2", "3"),
                url = "my.awesome.url",
                created = "XX-XX-2024"
            )

            repository.getLocationByName(DUMMY_LOCATION_NAME).collect { result ->
                coEvery { api.fetchLocationByName(DUMMY_LOCATION_NAME) }

                assertTrue(result is Result.Success)
                assertEquals(result.data.first(), expected)
            }
        }

    @Test
    fun `given valid location name, when calling getLocationByName method, then return failure mapped domain result`() =
        runTest(dispatcher) {
            val response: NetworkResult.Error<LocationWrapperDto?> = NetworkResult.Error<LocationWrapperDto?>(
                ERROR_CODE,
                "Something went wrong"
            )

            coEvery { api.fetchLocationByName(DUMMY_LOCATION_NAME) } returns response

            repository.getLocationByName(DUMMY_LOCATION_NAME).collect { result ->
                coEvery { api.fetchLocationByName(DUMMY_LOCATION_NAME) }

                assertTrue(result is Result.Error && result.exception is NetworkError.Generic)
                with(result.exception) {
                    assertEquals(ERROR_CODE, (this as NetworkError.Generic).errorCode)
                    assertTrue(message.toString().isNotEmpty())
                }
            }
        }
}
