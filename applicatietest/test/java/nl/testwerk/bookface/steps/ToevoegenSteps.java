package nl.testwerk.bookface.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@CucumberContextConfiguration
@SpringBootTest
public class ToevoegenSteps {
    @Given("I have a Spring Boot application")
    public void iHaveASpringBootApplication() {
        log.info("De test runt!");
    }

    @When("I run the application")
    public void iRunTheApplication() {
    }

    @Then("it should work correctly")
    public void itShouldWorkCorrectly() {
    }
}
