package com.stathis.common.exceptions

/**
 * Represents a network error inside the application.
 */

sealed class NetworkError : Exception() {

    /**
     * Represents a generic [NetworkError]
     * @property errorCode : The server's response error code.
     * @property message: The server's response message.
     */

    data class Generic(
        val errorCode: Int,
        override val message: String
    ) : NetworkError()

    /**
     * Represents a timeout [NetworkError]
     * @property message: The server's response message.
     */

    data class Timeout(
        override val message: String
    ) : NetworkError()

    /**
     * Represents a connection [NetworkError]
     * @property message: The server's response message.
     */

    data class ConnectionIssue(
        override val message: String
    ) : NetworkError()
}
