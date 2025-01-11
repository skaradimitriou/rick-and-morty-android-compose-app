package com.stathis.network.util

import com.stathis.network.BuildConfig
import io.ktor.http.URLProtocol
import io.ktor.http.buildUrl
import java.net.URI

/**
 * Helper fun to get the full url for a specific api call
 * @param endpoint: The specific endpoint of each network call.
 */
internal fun String.toFullApiCallUrl(endpoint: String): String {
    val uri = URI(BuildConfig.API_URL.plus(endpoint))

    val fullUrl = buildUrl {
        protocol = URLProtocol.HTTPS
        host = uri.host.plus(uri.path)
    }.toString()

    return fullUrl
}
