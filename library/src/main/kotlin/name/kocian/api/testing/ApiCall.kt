package name.kocian.api.testing

import java.util.Locale

fun api(uri: String, method: HttpMethod, authorized: Boolean = false): Request {

    val filename = uri
        .trim('/')
        .replace("/", "-")
        .plus(".${method.name.toLowerCase(Locale.getDefault())}")
        .plus(".json")

    val payload = JsonPayload.readFile(filename)

    return Request(uri, payload, method)
}
