package name.kocian.api.testing.util

import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver

class MockWebServerExtension : AfterEachCallback, BeforeEachCallback, ParameterResolver {

    private var mockWebServer = MockWebServer()

    override fun beforeEach(context: ExtensionContext?) {
        mockWebServer = MockWebServer().apply { start(12345) }
    }

    override fun afterEach(context: ExtensionContext?) {
        mockWebServer.shutdown()
    }

    override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Boolean {
        return parameterContext.parameter.type === MockWebServer::class.java
    }

    override fun resolveParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext) =
        mockWebServer
}
