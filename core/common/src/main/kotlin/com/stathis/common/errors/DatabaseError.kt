package com.stathis.common.errors

/**
 * Represents a Database error inside the application.
 */

sealed class DatabaseError : Exception() {

    /**
     * Represents an error that the specific item was not found inside the local database.
     * @property message: The database's error message.
     */

    data class NotFound(override val message: String) : DatabaseError()
}
