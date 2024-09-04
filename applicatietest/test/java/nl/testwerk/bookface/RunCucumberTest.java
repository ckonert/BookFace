package nl.testwerk.bookface;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "applicatietest/test/resources/features",
        glue = "nl.testwerk.bookface.steps"
)

public class RunCucumberTest {
}
