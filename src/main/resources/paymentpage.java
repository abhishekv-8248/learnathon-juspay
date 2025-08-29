package juspay2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import io.github.bonigarcia.wdm.WebDriverManager;

public class mainfunctiontest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            driver.manage().window().maximize();
            driver.get("https://www.snapdeal.com/");

            try {
                WebElement notNowBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[text()='NOT NOW']")));
                notNowBtn.click();
            } catch (Exception e) {}

            WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("inputValEnter")));
            searchInput.sendKeys("pen");
            WebElement searchBtn = driver.findElement(By.cssSelector("button.searchformButton"));
            searchBtn.click();

            WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("div.product-tuple-image a")));
            String originalWindow = driver.getWindowHandle();
            firstProduct.click();

            Set<String> windowHandles = driver.getWindowHandles();
            for (String handle : windowHandles) {
                if (!handle.equals(originalWindow)) {
                    driver.switchTo().window(handle);
                    break;
                }
            }

            WebElement pincodeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("input[placeholder='Enter pincode']")));
            pincodeInput.clear();
            pincodeInput.sendKeys("485001");

            WebElement checkBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[normalize-space()='check']")));
            checkBtn.click();

            Thread.sleep(2000);

            WebElement buyNowButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@id='rzp-buy-button-id']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", buyNowButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", buyNowButton);

            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[src*='razorpay']")));

            WebElement phoneInput = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@id='contact']")));
            phoneInput.sendKeys("9131878172");

            WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@id='redesign-v15-cta']")));
            continueBtn.click();

            driver.switchTo().defaultContent();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // driver.quit();
        }
    }
}

