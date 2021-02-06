package name.kocian.api.testing

import name.kocian.api.testing.exception.FileNotFoundException
import java.io.File

object JsonPayload {

    fun readFile(filename: String): String {
        val file = "/$filename"

        return this::class.java.getResource(file)?.readText(Charsets.UTF_8)
            ?: throw FileNotFoundException("File not found \"${File(file).absolutePath}\"")
    }
}
