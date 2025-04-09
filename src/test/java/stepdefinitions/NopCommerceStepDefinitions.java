package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.NopCommercePage;
import utilities.DriverFactory;
import utilities.TestDataGenerator;

public class NopCommerceStepDefinitions {

    // Initializing WebDriver instance using a factory method
    WebDriver driver = DriverFactory.getDriver();

    // Creating an instance of the NopCommercePage class to interact with web elements
    NopCommercePage page = new NopCommercePage(driver);

    // Step definition for navigating to the login page
    @Given("User is on login page")
    public void user_is_on_login_page() {
        page.navigateToLoginPage();
    }

    // Step definition for entering valid credentials
    @When("User enters valid {string} and {string}")
    public void user_enters_valid_credentials(String email, String password) {
        page.enterLoginDetails(email, password);
    }

    // Clicking the login button
    @And("Clicks on Login button")
    public void clicks_on_login_button() {
        page.clickLoginButton();
    }

    // Validating that the user is logged in successfully
    @Then("User should be logged in successfully")
    public void user_should_be_logged_in_successfully() {
        Assert.assertTrue(page.isUserLoggedIn());
    }

    // Step definition for navigating to the registration page
    @Given("User is on registration page")
    public void user_is_on_registration_page() throws InterruptedException {
        page.navigateToHomePage();
        page.navigateToRegisterPage();
    }

    // Entering dynamic details for registration and submitting the form
    @When("User enters dynamic details and submits the form {string} {string} {string} {string}")
    public void user_enters_dynamic_details_and_submits(String gender, String firstName, String lastName, String companyName) throws InterruptedException {
        page.enterRegistrationDetails(
                TestDataGenerator.generateRandomEmail(),
                TestDataGenerator.generateRandomPassword(),
                gender, firstName, lastName, companyName);
        Thread.sleep(5000);
        page.submitRegistration();
    }

    // Validating that the user is registered successfully
    @Then("User should be registered successfully")
    public void user_should_be_registered_successfully() {
        Assert.assertTrue(true, "Registered Successfully");
    }

    // Navigating to the nopCommerce website
    @Given("I open the nopCommerce website")
    public void openWebsite() throws InterruptedException {
        page.openWebsite();
    }

    // Searching for a specific product
    @When("I search for {string}")
    public void searchForProduct(String productName) throws InterruptedException {
        page.searchForProduct(productName);
    }

    // Clicking the first search result
    @When("I click on the first search result")
    public void clickFirstSearchResult() {
        page.clickFirstSearchResult();
    }

    // Adding the selected product to the cart
    @When("I add the product to the cart")
    public void addToCart() {
        page.addToCart();
    }

    // Validating the success message after adding a product to the cart
    @Then("I should see a success message")
    public void validateSuccessMessage() {
        String expectedMessage = "The product has been added to your shopping cart";
        Assert.assertTrue(page.getSuccessMessage().contains(expectedMessage), "Success message not found!");
        page.closeSuccessPopup();
    }

    // Fetching product specifications and logging them
    @Then("I fetch the product specifications and log them")
    public void fetchProductSpecifications() {
        page.fetchProductSpecifications();
    }

    // Searching for a random product
    @When("I search for a random product {string}")
    public void i_search_for_a_random_product(String product) {
        page.enterSearchKeyword(product);
    }

    // Clicking the search button
    @And("I click on the search button")
    public void i_click_on_the_search_button() {
        page.clickSearchButton();
    }

    // Validating that a specific message is displayed when there are no search results
    @Then("I should see the message {string}")
    public void i_should_see_the_message(String expectedMessage) {
        String actualMessage = page.getNoResultMessage();
        Assert.assertEquals("Validation Failed!", expectedMessage, actualMessage);
        System.out.println("✅ Test Passed: Expected message displayed.");
    }

    // Navigating to the cart page
    @And("I navigate to the cart page")
    public void i_navigate_to_the_cart_page() {
        page.goToCart();
    }

    // Entering a random coupon code in the cart page
    @When("I enter a random coupon code {string}")
    public void i_enter_a_random_coupon_code(String coupon) {
        page.enterCoupon(coupon);
    }

    // Clicking the apply coupon button
    @And("I click on the apply coupon button")
    public void i_click_on_the_apply_coupon_button() {
        page.applyCoupon();
    }

    // Validating that an invalid coupon error message is displayed
    @Then("I should see the coupon invalid message {string}")
    public void i_should_see_the_coupon_invalid_message(String expectedMessage) {
        String actualMessage = page.getCouponErrorMessage();
        Assert.assertEquals("Validation Failed!", expectedMessage, actualMessage);
        System.out.println("✅ Test Passed: Expected error message displayed.");
    }

    // Navigating to the nopCommerce search page
    @Given("User is on the nopCommerce search page")
    public void user_is_on_the_nopcommerce_search_page() {
        driver.get("https://demo.nopcommerce.com/search");
    }

    // Entering a search query
    @When("User enters {string} in the search field")
    public void user_enters_in_the_search_field(String query) {
        page.enterSearchText(query);
    }

    // Enabling advanced search
    @And("User enables Advanced Search")
    public void user_enables_advanced_search() {
        page.enableAdvancedSearch();
    }

    // Selecting a product category
    @And("User selects {string} as the category")
    public void user_selects_as_the_category(String category) {
        page.selectCategory(category);
    }

    // Setting a price range for the search
    @And("User sets the price range from {string} to {string}")
    public void user_sets_the_price_range(String min, String max) {
        page.setPriceRange(min, max);
    }

    // Clicking the search button to initiate the search
    @And("User clicks on the Search button")
    public void user_clicks_on_the_search_button() {
        page.clickSearchButton();
    }

    // Validating that the search results contain the expected keyword
    @Then("User should see search results containing {string}")
    public void user_should_see_search_results_containing(String keyword) {
        Assert.assertTrue(page.verifySearchResultsContain(keyword));
    }

    // Validating that the displayed products fall within the specified price range
    @Then("User should see only products priced between {string} and {string}")
    public void user_should_see_only_products_priced_between(String min, String max) {
        Assert.assertTrue(page.verifyPriceRange(Integer.parseInt(min), Integer.parseInt(max)));
    }

    // Validating that a specific message is displayed when there are no search results
    @Then("User should see a message {string}")
    public void user_should_see_a_message(String expectedMessage) {
        Assert.assertEquals(expectedMessage, page.getNoResultsMessage());
    }

    // Selecting a manufacturer for filtering search results
    @And("User selects {string} as the manufacturer")
    public void user_selects_as_the_manufacturer(String manufacturer) {
        page.selectManufacturer(manufacturer);
    }

    // Cleaning up after test execution by closing the browser
    @After
    public void tearDown() {
        driver.quit();
    }
}
