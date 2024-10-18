package com.stathis.common.util

import kotlin.test.Test
import kotlin.test.assertEquals

class MapperExtTest {

    @Test
    fun `given null String input, when calling toNotNull(), returns non-null String`() {
        val input: String? = null
        val given = input.toNotNull()
        val expected: String = ""
        assertEquals(given, expected)
    }

    @Test
    fun `given String input, when calling toNotNull(), returns non-null String`() {
        val input: String? = "Hello"
        val given = input.toNotNull()
        val expected: String = "Hello"
        assertEquals(given, expected)
    }

    @Test
    fun `given null Int input, when calling toNotNull(), returns non-null Int`() {
        val input: Int? = null
        val given = input.toNotNull()
        val expected: Int = 0
        assertEquals(given, expected)
    }

    @Test
    fun `given Int input, when calling toNotNull(), returns non-null Int`() {
        val input: Int? = 0
        val given = input.toNotNull()
        val expected: Int = 0
        assertEquals(given, expected)
    }

    @Test
    fun `given null Double input, when calling toNotNull(), returns non-null Int`() {
        val input: Double? = null
        val given = input.toNotNull()
        val expected: Double = 0.0
        assertEquals(given, expected)
    }

    @Test
    fun `given Double input, when calling toNotNull(), returns non-null Int`() {
        val input: Double? = 0.0
        val given = input.toNotNull()
        val expected: Double = 0.0
        assertEquals(given, expected)
    }

    @Test
    fun `given null list input, when calling toNotNull(), returns empty list`() {
        val input: List<String>? = null
        val given = input.toNotNull()
        assertEquals(given, listOf())
    }

    @Test
    fun `given empty list as input, when calling toNotNull(), returns empty list`() {
        val input: List<String>? = listOf()
        val given = input.toNotNull()
        assertEquals(given, listOf())
    }

    @Test
    fun `given null list as input, when calling toListOf(), returns empty list`() {
        val input: List<String>? = null
        val given = input.toListOf {}
        assertEquals(given, listOf())
    }

    @Test
    fun `given list of Int as input, when calling toListOf(), transforms to list of String`() {
        val input: List<Int> = listOf(1, 2, 3)
        val given = input.toListOf { it.toString() }
        val expected = listOf("1", "2", "3")
        assertEquals(given, expected)
    }
}
