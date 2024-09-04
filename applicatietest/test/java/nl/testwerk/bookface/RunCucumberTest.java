package nl.testwerk.bookface;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "applicatietest/test/resources/features",
        glue = "nl.testwerk.bookface.steps"
)

public class RunCucumberTest {
}
