package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Loginpage {
    WebDriver driver;
    By loginBtn = By.xpath("//span[text()='Login']");
    By usernameField = By.id("userName"); 
    By passwordField = By.id("password");
    By submitBtn = By.xpath("//button[text()='Login']");

    public Loginpage(WebDriver driver){
        this.driver = driver;
    }

    public void login(String username, String password){
        driver.findElement(loginBtn).click();
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(submitBtn).click();
    }
}
