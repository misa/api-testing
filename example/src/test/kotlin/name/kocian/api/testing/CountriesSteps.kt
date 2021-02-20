package name.kocian.api.testing

import io.cucumber.java8.En
import name.kocian.api.testing.HttpMethod.GET
import org.junit.Assert.assertEquals

private const val HOST = "https://restcountries.eu/rest/v2/"

class CountriesSteps : En {

    var countriesFound: Int? = 0
    var countryName: String = ""

    init {
        When<String>("I search for {string}") { searchText ->
            val result = Request(GET, HOST + "name/$searchText")
                .execute()

            countriesFound = result.body?.arraySize
            countryName = result.valueOf(1, "name") as String
        }

        Then<Int>("I find {int} result") { searchResultsCount ->
            assertEquals(searchResultsCount, countriesFound)
        }

        Then<String>("Name is {string}") { expectedName ->
            assertEquals(expectedName, countryName)
        }
    }
}
