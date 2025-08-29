package pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Homepage {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    JavascriptExecutor js;
    
   
    By searchInput = By.id("inputValEnter");
    By searchButton = By.xpath("//i[@class='sd-icon sd-icon-search-under-catagory lfloat']");
    By searchResultHeader = By.cssSelector("#searchMessageContainer h1, .search-result-txt, .srp-header");
    
    public Homepage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
    }
    
  
    public void searchProduct(String productName) {
        try {
            System.out.println("Searching for product: " + productName);
            
         
            WebElement searchBox = null;
            try {
                searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
            } catch (Exception e) {
                System.out.println("Primary search input not found, trying alternatives.");
                searchBox = findElementWithFallbacks(
                    "#inputValEnter",
                    "input[placeholder*='Search']",
                    ".search-box-wrapper input",
                    "input[type='text']"
                );
            }
            
            if (searchBox != null) {
                searchBox.clear();
                searchBox.sendKeys(productName);
                
                
                WebElement searchBtn = findElementWithFallbacks(
                    ".searchformButton",
                    ".search-submit", 
                    "button[onclick*='submitSearchForm']",
                    ".search-trigger"
                );
                
                if (searchBtn != null) {
                    searchBtn.click();
                } else {
               
                    searchBox.sendKeys(org.openqa.selenium.Keys.ENTER);
                }
                
                
                wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(searchResultHeader),
                    ExpectedConditions.urlContains("search"),
                    ExpectedConditions.presenceOfElementLocated(By.cssSelector(".product-tuple, .product-item"))
                ));
                
                System.out.println("Search completed successfully");
            } else {
                throw new RuntimeException("Could not find search input field");
            }
            
        } catch (Exception e) {
            System.out.println(" Search failed: " + e.getMessage());
            throw e;
        }
    }
    
    
    public void addFirstAvailableProductToCart() throws Exception {
        try {
            System.out.println(" Starting to add product to cart.");
            
           
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
            Thread.sleep(2000); 
            
            
            List<WebElement> products = findProductsOnPage();
            
            if (products.isEmpty()) {
                System.out.println("No products found on current page, trying to search for a product...");
               
                searchProduct("mobile");
                Thread.sleep(2000);
                products = findProductsOnPage();
            }
            
            if (!products.isEmpty()) {
                System.out.println("Found " + products.size() + " products");
                
               
                WebElement firstProduct = products.get(0);
                addProductToCart(firstProduct);
                
            } else {
                throw new RuntimeException("No products found on homepage or search results");
            }
            
        } catch (Exception e) {
            System.out.println(" Failed to add product to cart: " + e.getMessage());
            debugPageState();
            throw e;
        }
    }
    
   
    private List<WebElement> findProductsOnPage() {
        String[] productSelectors = {
            ".product-tuple:not([style*='display: none'])",
            ".product-item:not([style*='display: none'])", 
            ".product-card:not([style*='display: none'])",
            "[data-clickstream*='product']",
            "a[href*='/product/']",
            ".deals-container .product-image",
            ".gridView .product"
        };
        
        for (String selector : productSelectors) {
            List<WebElement> products = driver.findElements(By.cssSelector(selector));
            if (!products.isEmpty()) {
                System.out.println("Found products using selector: " + selector);
                return products;
            }
        }
        
        return driver.findElements(By.cssSelector("*")); 
    }
    
    
    private void addProductToCart(WebElement productElement) throws Exception {
        try {
          
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", productElement);
            Thread.sleep(1000);
            
            
            WebElement addToCartBtn = null;
            try {
                addToCartBtn = productElement.findElement(By.xpath(".//button[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'add to cart')]"));
            } catch (Exception e) {
                System.out.println("Direct add to cart button not found, trying product click...");
            }
            
            if (addToCartBtn != null) {
                
                wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
                addToCartBtn.click();
                System.out.println("Clicked Add to Cart button directly");
            } else {
               
                System.out.println("Clicking product to open product page...");
                wait.until(ExpectedConditions.elementToBeClickable(productElement));
                productElement.click();
                
                
                wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("/product/"),
                    ExpectedConditions.presenceOfElementLocated(By.cssSelector(".pdp-container, .product-details"))
                ));
                
                System.out.println("Product page opened: " + driver.getCurrentUrl());
                
                
                WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'add to cart') or contains(@class, 'add-cart')]")
                ));
                
                cartButton.click();
                System.out.println("Successfully added product to cart from product page");
            }
            
           
            Thread.sleep(2000);
            
        } catch (Exception e) {
            System.out.println("Failed to add product to cart: " + e.getMessage());
            throw e;
        }
    }
    
    
    private WebElement findElementWithFallbacks(String... selectors) {
        for (String selector : selectors) {
            try {
                List<WebElement> elements = driver.findElements(By.cssSelector(selector));
                if (!elements.isEmpty()) {
                    return elements.get(0);
                }
            } catch (Exception e) {
                
            }
        }
        return null;
    }
    
   
    private void debugPageState() {
        System.out.println("\n=== DEBUG PAGE STATE ===");
        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Page Title: " + driver.getTitle());
        
     
        int divCount = driver.findElements(By.tagName("div")).size();
        int linkCount = driver.findElements(By.tagName("a")).size();
        int productLikeElements = driver.findElements(By.cssSelector("[class='product'], [class='item'], [class='card']")).size();
        
        System.out.println("DIV elements: " + divCount);
        System.out.println("Link elements: " + linkCount); 
        System.out.println("Product-like elements: " + productLikeElements);
        
       
        boolean loggedIn = !driver.findElements(By.cssSelector(".accountUserName, .user-name, .logged-in")).isEmpty();
        System.out.println("Logged in status: " + loggedIn);
        System.out.println("========================\n");
    }
}