package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class testPages {


    WebDriver driver;

    public testPages(WebDriver driver) {
        this.driver = driver;
    }

    public void openBrowser(String url) throws InterruptedException {
        driver.get(url);
    }

    public void enterKeyword(String name) throws InterruptedException {
        driver.findElement(By.xpath("//textarea[@title='Search']")).sendKeys(name);
        driver.findElement(By.xpath("//textarea[@title='Search']")).sendKeys(Keys.ENTER);
        Thread.sleep(20000);
    }

   public void closeDriver(){
        driver.quit();
   }




}
