package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Loginpage {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    By signInButton = By.xpath("//span[text()='Sign In']");
    By loginLink = By.xpath("//a[text()='login']");
    By loginIframe = By.id("loginIframe");
    By mobileNumberField = By.id("userName");
    By continueButton = By.id("checkUser");

    public Loginpage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    public void login(String mobileNumber) throws Exception {
      
        WebElement signIn = wait.until(ExpectedConditions.visibilityOfElementLocated(signInButton));
        actions.moveToElement(signIn).perform();

 
        WebElement login = wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        login.click();

  
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[id*='login']")));

    
        WebElement mobileField = wait.until(ExpectedConditions.visibilityOfElementLocated(mobileNumberField));
        mobileField.sendKeys(mobileNumber);

        driver.findElement(continueButton).click();

    
        System.out.println("Enter OTP manually in browser, then press Enter in console...");
        System.in.read();

     
        driver.switchTo().defaultContent();

        
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector(".accountUserName, .user-name, .logged-in")
        ));

        System.out.println("Login successful!");
    }
}