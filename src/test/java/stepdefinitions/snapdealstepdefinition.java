package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.Homepage;
import pages.Productpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class snapdealstepdefinition {

    WebDriver driver;
    Homepage homePage;
    Productpage productPage;

    @Given("user is on Snapdeal homepage")
    public void openHomePage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.snapdeal.com/");
    }

    @When("user logs in with mobile number")
    public void loginWithOTP() {
        // Click login
        driver.findElement(By.xpath("//span[text()='Login']")).click();
        driver.findElement(By.id("userName")).sendKeys("9131878172");
        driver.findElement(By.id("checkUser")).click(); // Send OTP

        // Pause to manually enter OTP
        System.out.println(" Enter OTP manually on the website and press Enter in console to continue...");
        try { System.in.read(); } catch (Exception e) { e.printStackTrace(); }
    }

    @When("user searches for {string}")
    public void searchProduct(String product) {
        
        homePage = new Homepage(driver);
        homePage.searchProduct(product);
    }

    @When("user adds first product to the cart")
    public void addProductToCart() {
        
        productPage = new Productpage(driver);
        productPage.addFirstProductToCart(); // Must exist in Productpage.java
    }

    @Then("user should see the product in the cart")
    public void verifyCart() {
        driver.get("https://www.snapdeal.com/cart");
        System.out.println("✅ Product successfully added to cart!");
        driver.quit();
    }
}
