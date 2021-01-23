package name.kocian.api.testing

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

class Request(
    private val uri: String,
    private var payload: String,
    private val method: HttpMethod,
    private var headers: MutableMap<String, String> = mutableMapOf(),
    private val authorized: Boolean = true
) {
    fun headers(vararg headers: Pair<String, String>): Request {
        this.headers.putAll(headers)
        return this
    }

    fun payload(vararg replacements: Pair<String, String>): Request {
        replacements.forEach { replacement ->
            payload = payload.replace("{{${replacement.first}}}", replacement.second)
        }
        return this
    }

    @Suppress("MagicNumber")
    fun execute(): Response {
        println("<<< Request: $method /$uri")
        println("Headers: $headers")
        println(payload.trim())

        // TODO Execute request
        val response = Response()
        response.code = 200
        response.body = Json.parseToJsonElement(payload) as JsonObject

        println(">>> ${response.code} Response: $method /$uri")
        println(response.body.toString())

        return response
    }
}
