package name.kocian.api.testing

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@CucumberOptions(
    glue = ["name.kocian.api.testing"],
    features = ["classpath:/features"]
)
@RunWith(Cucumber::class)
class RunCucumberTest
