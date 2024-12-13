package com.stathis.common.exceptions

/**
 * Represents a generic error inside the application.
 */

class GenericError(
    override val message: String
) : Exception()
