package name.kocian.api.testing

import name.kocian.api.testing.HttpMethod.POST
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ApiTest {

    @Test
    fun sampleApiTest() {

        val response = api("v1/example", POST)
            .headers(
                "sample" to "test"
            )
            .payload(
                "name" to "Michal Kocian",
                "library" to "api-testing"
            )
            .execute()

        // API response body
        // { "message": "Success" }

        assertEquals(200, response.code)
        assertEquals("Success", response.element("message"))
    }
}
