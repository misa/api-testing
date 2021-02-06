package name.kocian.api.testing

import name.kocian.api.testing.exception.ApiTestingException
import name.kocian.api.testing.util.MockWebServerExtension
import name.kocian.api.testing.util.enqueue
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@DisplayName("GET request")
@ExtendWith(MockWebServerExtension::class)
class GetRequestTest {

    private val hostname = "http://localhost:12345/"

    @Test
    @DisplayName("Simple GET request")
    fun simpleGet(mockWebServer: MockWebServer) {
        mockWebServer.enqueue {
            setResponseCode(200)
        }

        val response = Request(HttpMethod.GET, hostname + "login")
            .execute()

        val request = mockWebServer.takeRequest()

        assertEquals("GET", request.method)
        assertEquals("/login", request.path)
        assertEquals(4, request.headers.size)

        assertEquals(200, response.code)
        assertEquals(null, response.body)
    }

    @Test
    @DisplayName("Set a custom header")
    fun customHeader(mockWebServer: MockWebServer) {
        mockWebServer.enqueue {
            setResponseCode(200)
        }

        val response = Request(HttpMethod.GET, hostname + "login")
            .headers(
                "SampleHeader" to "SampleValue"
            )
            .execute()

        val request = mockWebServer.takeRequest()

        assertEquals("GET", request.method)
        assertEquals("/login", request.path)
        assertEquals(5, request.headers.size)
        assertEquals("SampleValue", request.headers.get("SampleHeader"))

        assertEquals(200, response.code)
        assertEquals(null, response.body)
    }

    @Test
    @DisplayName("GET request with payload throws exception")
    fun getNoPayloadAllowed(mockWebServer: MockWebServer) {
        mockWebServer.enqueue {
            setResponseCode(200)
        }

        assertThrows<ApiTestingException> {
            Request(HttpMethod.GET, hostname + "login")
                .payload(
                    "api/login-post.json",
                    "name" to "Developer",
                    "pass" to "secret123"
                )
                .execute()
        }

        assertEquals(0, mockWebServer.requestCount)
    }

    @Test
    @DisplayName("Complex response")
    fun complex(mockWebServer: MockWebServer) {
        mockWebServer.enqueue {
            setResponseCode(200)
            setBody(JsonPayload.readFile("response.json"))
        }

        val response = Request(HttpMethod.GET, hostname + "complex").execute()

        assertEquals(200, response.code)

        assertEquals(3, response.valueOf("page", 1))

        assertEquals("API", response.valueOf("products", 1, "name"))
        assertEquals(5, response.valueOf("products", 1, "version"))

        assertEquals("Android", response.valueOf("products", 2, "name"))
        assertEquals(11, response.valueOf("products", 2, "version"))
    }
}
