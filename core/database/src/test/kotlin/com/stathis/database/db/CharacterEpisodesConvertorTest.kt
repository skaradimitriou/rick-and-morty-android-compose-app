package com.stathis.database.db

import com.stathis.database.db.characters.CharacterEpisodesConvertor
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CharacterEpisodesConvertorTest {

    private val convertor = CharacterEpisodesConvertor()

    companion object {

        private const val CHARACTER_EPISODES_JSON: String = "[\"1\",\"2\",\"3\"]"
        private val EPISODE_LIST: List<String> = intArrayOf(1, 2, 3).map { it.toString() }
    }

    /**
     * Tests that fromEpisodesToString convertor method works as expected.
     * (converts a List<String> to a Json String)
     */

    @Test
    fun `given list of episodes, when calling fromEpisodesToString, then return json string`() {
        val episodes: List<String> = EPISODE_LIST
        val result: String = convertor.fromEpisodesToString(episodes)
        assertEquals(result, CHARACTER_EPISODES_JSON)
    }

    /**
     * Tests that fromStringToEpisodesList convertor method works as expected.
     * (converts a Json String to a List<String>)
     */

    @Test
    fun `given json string, when calling fromStringToEpisodesList, then return list of episodes`() {
        val episodesInJsonString: String = CHARACTER_EPISODES_JSON
        val result: List<String> = convertor.fromStringToEpisodesList(episodesInJsonString)
        assertEquals(result, EPISODE_LIST)
    }
}
