package name.kocian.api.testing

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import name.kocian.api.testing.exception.ApiTestingException

val JsonElement.arraySize: Int
    get() {
        if (this !is JsonArray) {
            throw ApiTestingException("It's not an array.")
        } else {
            return this.size
        }
    }
