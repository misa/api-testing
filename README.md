# api-testing
Kotlin helpers for API testing

# Example
```kotlin
@Test
fun sampleApiTest() {
    
    val response = api("v1/example", POST)
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
    
    assertEquals(201, response.code)
    assertEquals("Success", response.element("message"))
}
```

## License
The API Testing Framework is released under version 2.0 of the [Apache License][license].
