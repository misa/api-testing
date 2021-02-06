# api-testing
Kotlin helpers for API testing

# Example
```kotlin
@Test
fun sampleApiTest() {
    
    val response = Request(POST, hostname + "login")
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
    assertEquals("Success", response.valueOf("message"))
}
```

## License
The API Testing Framework is released under version 2.0 of the [Apache License][license].
