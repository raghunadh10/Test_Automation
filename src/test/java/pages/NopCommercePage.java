package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;

import java.util.List;


public class NopCommercePage {
    WebDriver driver;

    public NopCommercePage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By loginEmail = By.id("Email");
    By loginPassword = By.id("Password");
    By loginButton = By.cssSelector("button.login-button");
    By registerPageLink = By.linkText("Register");
    By wishlistButton = By.cssSelector(".add-to-wishlist-button");
    By cartButton = By.cssSelector(".add-to-cart-button");
    By logoutButton = By.linkText("Log out");

    private String firstNameXPath = "//input[@id='FirstName']";
    private String lastNameXPath = "//input[@id='LastName']";
    private String companyNameXPath = "//input[@id='Company']";
    private String genderMaleXPath = "//input[@name='Gender'][@value='M']";
    private String genderFemaleXPath = "//input[@name='Gender'][@value='F']";
    private String registerButtonXPath = "//button[@id='register-button']";


    By emailField = By.id("Email");
    By passwordField = By.id("Password");

    By searchBox = By.xpath("//input[@id='small-searchterms']");
    By firstSearchResult = By.xpath("(//h2[@class='product-title']/a)[1]");
    By addToCartButton = By.xpath("//button[@id='add-to-cart-button-4']");
    By successMessage = By.xpath("//p[@class='content']");
    By closePopup = By.xpath("//span[@class='close']");
    By productTitle = By.xpath("//h1[@itemprop='name']");
    By sku = By.xpath("//div[@class='sku']/span[@class='value']");
    By price = By.xpath("//span[@itemprop='price']");

    By searchBox1 = By.id("small-searchterms");
    By searchButton = By.xpath("//button[@type='submit' and contains(text(),'Search')]");
    By noResultMessage = By.xpath("//div[@class='no-result']");

    By cartLink = By.xpath("//a[contains(text(),'Shopping cart')]");
    By couponInput = By.xpath("//input[@id='discountcouponcode']");
    By applyCouponButton = By.xpath("//button[@name='applydiscountcouponcode']");
    By couponErrorMessage = By.xpath("//div[@class='message']/span");


    By advancedSearchCheckbox = By.id("advs");
    By categoryDropdown = By.id("cid");
    By minPriceField = By.id("pf");
    By maxPriceField = By.id("pt");
    By searchResults = By.cssSelector(".product-title a");
    By noResultsMessage = By.xpath("//div[contains(text(),'No products were found')]");
    By manufacturerDropdown = By.id("mid");

    public void selectManufacturer(String manufacturer) {
        Select manufacturerSelect = new Select(driver.findElement(manufacturerDropdown));
        manufacturerSelect.selectByVisibleText(manufacturer);
    }

    public void enterSearchText(String query) {
        driver.findElement(searchBox).sendKeys(query);
    }

    public void enableAdvancedSearch() {
        WebElement checkbox = driver.findElement(advancedSearchCheckbox);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void selectCategory(String category) {
        Select categorySelect = new Select(driver.findElement(categoryDropdown));
        categorySelect.selectByVisibleText(category);
    }

    public void setPriceRange(String min, String max) {
        driver.findElement(minPriceField).clear();
        driver.findElement(minPriceField).sendKeys(min);
        driver.findElement(maxPriceField).clear();
        driver.findElement(maxPriceField).sendKeys(max);
    }


    public boolean verifySearchResultsContain(String keyword) {
        List<WebElement> results = driver.findElements(searchResults);
        for (WebElement result : results) {
            if (!result.getText().toLowerCase().contains(keyword.toLowerCase())) {
                return false;
            }
        }
        return !results.isEmpty();
    }

    public boolean verifyPriceRange(int min, int max) {
        List<WebElement> results = driver.findElements(searchResults);
        for (WebElement result : results) {
            String priceText = result.findElement(By.xpath("//span[@class='price actual-price']")).getText();
            double price = Double.parseDouble(priceText.replace("$", ""));
            if (price < min || price > max) {
                return false;
            }
        }
        return !results.isEmpty();
    }

    public String getNoResultsMessage() {
        return driver.findElement(noResultsMessage).getText();
    }

    public void goToCart() {
        driver.findElement(cartLink).click();
    }

    public void enterCoupon(String coupon) {
        WebElement couponField = driver.findElement(couponInput);
        couponField.clear();
        couponField.sendKeys(coupon);
    }

    public void applyCoupon() {
        driver.findElement(applyCouponButton).click();
    }

    public String getCouponErrorMessage() {
        return driver.findElement(couponErrorMessage).getText();
    }

    public void enterSearchKeyword(String keyword) {
        driver.findElement(searchBox1).clear();
        driver.findElement(searchBox1).sendKeys(keyword);
    }

    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }

    public String getNoResultMessage() {
        return driver.findElement(noResultMessage).getText();
    }


    public void openWebsite() throws InterruptedException {
        driver.get("http://demo.nopcommerce.com/");
    }

    public void searchForProduct(String productName) throws InterruptedException {
        driver.findElement(searchBox).sendKeys(productName);
        driver.findElement(searchBox).submit();
    }

    public void clickFirstSearchResult() {
        driver.findElement(firstSearchResult).click();
    }

    public void addToCart() {
        driver.findElement(addToCartButton).click();
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }

    public void closeSuccessPopup() {
        driver.findElement(closePopup).click();
    }

    public void fetchProductSpecifications() {
        System.out.println("Product Title: " + driver.findElement(productTitle).getText());
        System.out.println("SKU: " + driver.findElement(sku).getText());
        System.out.println("Price: " + driver.findElement(price).getText());
    }

    public void navigateToLoginPage() {
        driver.get("https://demo.nopcommerce.com/login");
    }

    public void navigateToHomePage() {
        driver.get("https://demo.nopcommerce.com/");
    }

    public void enterLoginDetails(String email, String password) {
        driver.findElement(loginEmail).sendKeys(email);
        driver.findElement(loginPassword).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public boolean isUserLoggedIn() {
        return driver.findElement(logoutButton).isDisplayed();
    }

    public void navigateToRegisterPage() {
        driver.findElement(By.xpath("//a[contains(text(),'Register')]")).click();
    }

    public void enterRegistrationDetails(String email, String password,String gender,String firstName,String lastName,String companyName) {
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(password);

        if (gender.equalsIgnoreCase("Male")) {
            driver.findElement(By.xpath(genderMaleXPath)).click();
        } else if (gender.equalsIgnoreCase("Female")) {
            driver.findElement(By.xpath(genderFemaleXPath)).click();
        }

        // Enter First Name
        WebElement firstNameField = driver.findElement(By.xpath(firstNameXPath));
        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        // Enter Last Name
        WebElement lastNameField = driver.findElement(By.xpath(lastNameXPath));
        lastNameField.clear();
        lastNameField.sendKeys(lastName);

        // Enter Company Name
        WebElement companyNameField = driver.findElement(By.xpath(companyNameXPath));
        companyNameField.clear();
        companyNameField.sendKeys(companyName);
    }

    public void submitRegistration() {
        driver.findElement(By.xpath(registerButtonXPath)).click();
    }

    public void login(String email, String password) {
        driver.get("https://demo.nopcommerce.com/login");
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}
