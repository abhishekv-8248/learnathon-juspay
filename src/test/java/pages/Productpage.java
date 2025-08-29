package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Productpage {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    public Productpage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);
    }

    public void addProductToCartByName(String partialProductName) {
       
        By availableProductContainerLocator = By.xpath(
            "(//div[contains(@class, 'product-tuple-listing') and .//p[contains(@title, '" + partialProductName + "')] and not(.//div[contains(text(), 'Sold Out')])])[1]"
        );
        
        WebElement productContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(availableProductContainerLocator));

        actions.moveToElement(productContainer).perform();
        
       
        By addToCartButtonLocator = By.xpath(".//div[contains(@class, 'add-to-cart-button')]");
        WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(productContainer.findElement(addToCartButtonLocator)));
        
      
        cartButton.click();
    }
}