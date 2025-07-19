package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.NopCommercePage;
import pages.testPages;
import utilities.DriverFactory;

public class test1 {


    // Initializing WebDriver instance using a factory method


    WebDriver driver = DriverFactory.getDriver();

    // Creating an instance of the testPages class to interact with web elements
    testPages testPages = new testPages(driver);


    @Given("i open a google web browser")
    public void openGoogleBrowser() throws InterruptedException {
        testPages.openBrowser("https://www.google.com");
    }

    @Then("i enter youtube in search bar and click")
    public void iEnterYoutubeInSearchBarAndClick() throws InterruptedException {
        testPages.enterKeyword("youtube");
    }

    @Then("i will close the driver")
    public void iWillCloseTheDriver() {
        testPages.closeDriver();
    }
}
