package stepDefinations;

import io.cucumber.java.en.*;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.Homepage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapdealStepDefinition {

    WebDriver driver;
    Homepage homePage;
    WebDriverWait wait;

    @Given("user is on Snapdeal homepage")
    public void openHomePage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://www.snapdeal.com/");

        try {
            By notNowBtn = By.xpath("//button[text()='NOT NOW']");
            WebElement notNow = wait.until(ExpectedConditions.elementToBeClickable(notNowBtn));
            notNow.click();
            System.out.println("Closed popup by clicking NOT NOW");
        } catch (Exception e) {
            System.out.println("Popup not displayed, continuing.");
        }
    }


    @When("user logs in with mobile number")
    public void loginWithOTP() {
        Actions actions = new Actions(driver);
        try {
            WebElement signInButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Sign In']")));
            actions.moveToElement(signInButton).perform();

            WebElement loginLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='login']")));
            loginLink.click();

            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[id*='login']")));

            WebElement mobileNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName")));
            mobileNumberField.sendKeys("9109107881");
            driver.findElement(By.id("checkUser")).click();

            System.out.println("Please enter OTP manually in browser. Waiting 30 seconds...");
            Thread.sleep(30000);

            driver.switchTo().defaultContent();

            WebElement accountLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".accountUserName, .user-name, .logged-in")));
            System.out.println("Logged in as: " + accountLabel.getText());

            try {
                WebElement notNow = driver.findElement(By.xpath("//button[text()='NOT NOW']"));
                if (notNow.isDisplayed()) {
                    notNow.click();
                    System.out.println("Notification popup dismissed.");
                }
            } catch (Exception e) {
                System.out.println("No notification popup found.");
            }

            WebElement searchBar = wait.until(ExpectedConditions.elementToBeClickable(By.id("inputValEnter")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchBar);
            searchBar.clear();
            searchBar.sendKeys("pen");

            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.searchformButton")));
            searchButton.click();
            System.out.println("Searching for pen.");

        } catch (Exception e) {
            System.out.println("Login or search failed: " + e.getMessage());
            e.printStackTrace();
            if (driver != null) driver.quit();
        }
    }

    @When("user adds the first available product to the cart")
    public void addFirstAvailableProductToCart() throws Exception {
        homePage = new Homepage(driver);
        homePage.addFirstAvailableProductToCart();
    }

    @Then("user should see the product in the cart")
    public void verifyCart() {
        try {
            Thread.sleep(2000);
            driver.get("https://www.snapdeal.com/cart");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'cart-item-list') and .//div[@class='cart-item']]")));
            System.out.println("Product successfully added to cart and verified!");
        } catch (Exception e) {
            System.out.println("Verification failed. Product not found in cart.");
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
