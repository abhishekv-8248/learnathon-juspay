package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Homepage {
    WebDriver driver;
    By searchBox = By.name("keyword");
    By searchButton = By.className("searchformButton");

    public Homepage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchProduct(String product) {
        driver.findElement(searchBox).sendKeys(product);
        driver.findElement(searchButton).click();
    }
}
