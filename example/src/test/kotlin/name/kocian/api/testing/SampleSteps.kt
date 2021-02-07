package name.kocian.api.testing

import io.cucumber.java8.En
import kotlinx.serialization.json.JsonArray
import org.junit.Assert.assertEquals

var linesAvailable: Int = 0

class SampleSteps : En {

    init {
        Given("Underground status is available") {
        }

        When("I get current status") {
            val result = Request(HttpMethod.GET, "https://api.tfl.gov.uk/line/mode/tube/status").execute()

            assertEquals("Bakerloo", result.valueOf(1, "name"))

            linesAvailable = (result.body as JsonArray).size
        }

        Then<Int>("There is {int} lines available") {
            assertEquals(it, linesAvailable)
        }
    }
}
