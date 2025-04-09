package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",  // Path to feature files
        glue = {"stepdefinitions"},  // Path to step definition files
        plugin = {
                "pretty",  // Prints readable console output
                "html:target/cucumber-reports/cucumber.html",  // Generates HTML reports
                "json:target/cucumber-reports/cucumber.json",  // Generates JSON reports
                "junit:target/cucumber-reports/cucumber.xml"  // Generates JUnit XML reports
        },
        monochrome = true, // Clear console output
        dryRun = false, // Set to 'true' to check step definitions without execution
        tags = "@test"  // Run only scenarios with this tag
)
public class TestRunner {
}
