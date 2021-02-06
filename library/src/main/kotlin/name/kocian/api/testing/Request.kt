package name.kocian.api.testing

import name.kocian.api.testing.exception.ApiTestingException

class Request(
    val method: HttpMethod,
    val url: String,
    var headers: MutableMap<String, String> = mutableMapOf()
) {
    var payload: String = ""

    fun headers(vararg headers: Pair<String, String>): Request {
        this.headers.putAll(headers)
        return this
    }

    fun payload(vararg replacements: Pair<String, String>?): Request {
        if (method in listOf(HttpMethod.GET, HttpMethod.HEAD)) {
            throw ApiTestingException("$method request can't have payload.")
        }

        replacements.forEach { replacement ->
            payload = payload.replace("{{${replacement?.first}}}", replacement?.second ?: "").trim()
        }

        return this
    }

    fun payload(file: String, vararg replacements: Pair<String, String>?): Request {
        if (method in listOf(HttpMethod.GET, HttpMethod.HEAD)) {
            throw ApiTestingException("$method request can't have payload.")
        }

        payload = JsonPayload.readFile(file).trim()

        replacements.forEach { replacement ->
            payload = payload.replace("{{${replacement?.first}}}", replacement?.second ?: "").trim()
        }

        return this
    }

    fun execute(): Response {
        return NetworkClient.execute(this)
    }
}
