package name.kocian.api.testing.util

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

fun MockWebServer.enqueue(mockResponse: MockResponse.() -> Unit) {
    val mock = MockResponse()
    mockResponse.invoke(mock)
    this.enqueue(mock)
}
