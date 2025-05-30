package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.DriverFactory;
import utilities.screenshotUtil;

import java.sql.DriverManager;

import static utilities.DriverFactory.getDriver;

public class Hooks {

    @Before
    public void setUp() {
        // Initialize driver before each scenario
        DriverFactory.getDriver();
    }
//    @After
//    public void tearDown(Scenario scenario) {
//        if (scenario.isFailed()) {
//            screenshotUtil.takeScreenshot(getDriver(), scenario.getName());
//        }
//        DriverFactory.quitDriver();
//    }
}
