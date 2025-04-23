package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import utilities.screenshotUtil;

import static utilities.DriverFactory.getDriver;

public class Hooks {

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            screenshotUtil.takeScreenshot(getDriver(), scenario.getName());
        }
    }
}
