package name.kocian.api.testing

const val DEFAULT_TIMEOUT = 5L

object Configuration {
    var timeout: Long = DEFAULT_TIMEOUT
    var storeResponses = true
}
