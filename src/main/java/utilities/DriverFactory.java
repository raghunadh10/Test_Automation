package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

/**
 * Singleton factory class for managing the WebDriver instance.
 */
public class DriverFactory {
    private static WebDriver driver;

    /**
     * Initializes and returns the WebDriver instance if not already initialized.
     *
     * @return A singleton instance of {@link WebDriver}.
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();

            // Sets an implicit wait time for elements
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        }
        return driver;
    }
}
