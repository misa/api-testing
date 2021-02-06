package name.kocian.api.testing

import name.kocian.api.testing.util.MockWebServerExtension
import name.kocian.api.testing.util.enqueue
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@DisplayName("POST request")
@ExtendWith(MockWebServerExtension::class)
class PostRequestTest {

    private val hostname = "http://localhost:12345/"

    @Test
    @DisplayName("Simple POST request")
    fun simplePost(mockWebServer: MockWebServer) {
        mockWebServer.enqueue {
            setResponseCode(200)
        }

        val response = Request(HttpMethod.POST, hostname + "login")
            .execute()

        val request = mockWebServer.takeRequest()

        assertEquals("POST", request.method)
        assertEquals("/login", request.path)
        assertEquals(6, request.headers.size)

        assertEquals(200, response.code)
        assertEquals(null, response.body)
    }

    @Test
    @DisplayName("Load a payload from file")
    fun payload(mockWebServer: MockWebServer) {
        mockWebServer.enqueue {
            setResponseCode(200)
        }

        val response = Request(HttpMethod.POST, hostname + "login")
            .payload("payload-no-replacements.json")
            .execute()

        val request = mockWebServer.takeRequest()

        assertEquals("POST", request.method)
        assertEquals("/login", request.path)
        assertEquals(6, request.headers.size)

        assertEquals(200, response.code)
        assertEquals(null, response.body)
    }

    @Test
    @DisplayName("Load a payload from file and provide replacements")
    fun payloadWithReplacements(mockWebServer: MockWebServer) {
        mockWebServer.enqueue {
            setResponseCode(200)
        }

        val response = Request(HttpMethod.POST, hostname + "login")
            .payload(
                "payload-replacements.json",
                "name" to "Michal",
                "pass" to "secret"
            )
            .execute()

        val request = mockWebServer.takeRequest()

        assertEquals("POST", request.method)
        assertEquals("/login", request.path)
        assertEquals(6, request.headers.size)

        assertEquals(200, response.code)
        assertEquals(null, response.body)
    }
}
