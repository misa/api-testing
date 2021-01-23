package name.kocian.api.testing

import name.kocian.api.testing.exception.FileNotFoundException

object JsonPayload {

    fun readFile(filename: String): String {
        val file = "/api/$filename"

        return javaClass.getResource(file)?.readText(Charsets.UTF_8)
            ?: throw FileNotFoundException("File not found \"$file\"")
    }
}
