package name.kocian.api.testing

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import okhttp3.Headers.Companion.toHeaders
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import java.time.Duration.ofSeconds

object NetworkClient {

    private val client: OkHttpClient = OkHttpClient.Builder()
        .callTimeout(ofSeconds(Configuration.timeout))
        .connectTimeout(ofSeconds(Configuration.timeout))
        .readTimeout(ofSeconds(Configuration.timeout))
        .writeTimeout(ofSeconds(Configuration.timeout))
        .addInterceptor(
            HttpLoggingInterceptor {
                println(it.trim())
            }.also {
                it.level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()

    fun execute(request: Request): Response {

        val json = "application/json; charset=utf-8".toMediaType()

        val req = okhttp3.Request.Builder()
            .headers(request.headers.toHeaders())
            .url(request.url)
            .also {
                if (request.method in listOf(HttpMethod.POST)) {
                    it.method(request.method.name, request.payload.toRequestBody(json))
                } else {
                    it.method(request.method.name, null)
                }
            }
            .build()

        val res = client.newCall(req).execute()

        return Response().apply {
            original = res
            code = res.code
            val responseBody = res.body?.string()
            body = if (responseBody.isNullOrBlank()) {
                null
            } else {
                Json.decodeFromString<JsonElement>(responseBody)
            }
        }
    }
}
