package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Productpage {
    WebDriver driver;

    By firstProduct = By.cssSelector("div.product-tuple-image");
    By addToCart = By.xpath("//span[text()='add to cart']");

    // Constructor
    public Productpage(WebDriver driver) {
        this.driver = driver;
    }

    // ✅ THIS method must exist and be public
    public void addFirstProductToCart() {
        // Click on first product
        driver.findElement(firstProduct).click();

        // Switch to the new tab opened
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        // Click Add to Cart
        driver.findElement(addToCart).click();
    }
}
