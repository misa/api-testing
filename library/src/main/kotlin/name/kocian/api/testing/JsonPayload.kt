package name.kocian.api.testing

object JsonPayload {

    fun readFile(filename: String): String {
        val file = "/api/$filename"

        return javaClass.getResource(file)?.readText(Charsets.UTF_8)
            ?: throw Exception("File not found \"$file\"")
    }
}
