package com.arup.chatapp.services

import android.net.Uri
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

class NetworkService {
    private val client = HttpClient {
        install(HttpTimeout)
    }

    suspend fun login(baseUrl: String, requestBody: String): String {
        val uri = Uri.parse(baseUrl)
            .buildUpon()
            .appendEncodedPath("login")
            .build()
        return client.post(uri.toString()) {
            contentType(ContentType.Application.Json)
            body = requestBody
        }
    }
}
