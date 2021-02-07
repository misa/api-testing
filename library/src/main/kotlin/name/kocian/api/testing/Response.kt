package name.kocian.api.testing

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.float
import kotlinx.serialization.json.floatOrNull
import kotlinx.serialization.json.int
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonPrimitive
import name.kocian.api.testing.exception.ApiTestingException
import okhttp3.Response

class Response {

    lateinit var original: Response

    var code: Int = 0
    var body: JsonElement? = null

    @Suppress("ReturnCount")
    fun valueOf(vararg name: Any): Any {

        var currentElement: JsonElement = body ?: JsonPrimitive("")

        name.forEachIndexed { index, elementName ->

            when (val jsonElement = currentElement) {
                is JsonArray -> {
                    currentElement = jsonElement[-1 + elementName as Int]
                }
                is JsonObject -> {
                    currentElement =
                        jsonElement[elementName as String]
                        ?: throw ApiTestingException("Incorrect identifier: $elementName")
                }
                else -> {
                    if (index > 1) {
                        throw ApiTestingException("TODO Something went wrong")
                    }
                }
            }
        }

        val resultPrimitiveElement = currentElement.jsonPrimitive

        if (resultPrimitiveElement.isString) {
            return resultPrimitiveElement.content
        }

        if (resultPrimitiveElement.intOrNull != null) {
            return resultPrimitiveElement.int
        }

        if (resultPrimitiveElement.floatOrNull != null) {
            return resultPrimitiveElement.float
        }

        return resultPrimitiveElement.content
    }
}
