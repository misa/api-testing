package name.kocian.api.testing

import kotlinx.serialization.json.JsonObject

class Response {

    fun element(vararg name: Any): String {
        // TODO Parse response and return element
        return "Success"
    }

    var code: Int = 0
    var body: JsonObject = JsonObject(mapOf())
    var bodyByteArray: ByteArray = byteArrayOf()

    val string: String
        get() = body.toString()
}
