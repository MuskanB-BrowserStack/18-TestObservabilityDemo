package com.browserstack;

import com.browserstack.SeleniumTest;
import org.aspectj.lang.annotation.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class BStackDemoTest extends SeleniumTest {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        driver.manage().window().setSize(new Dimension(1470, 920));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void addProductToCart() throws Exception {
        // navigate to bstackdemo
        driver.get("https://www.bstackdemo.com");

        // Check the title
        Assert.assertTrue(driver.getTitle().matches("StackDemo"));

        // Save the text of the product for later verify
        String productOnScreenText = driver.findElement(By.xpath("//*[@id=\"1\"]/p")).getText();
        // Click on add to cart button
        driver.findElement(By.xpath("//*[@id=\"1\"]/div[4]")).click();

        // See if the cart is opened or not
        Assert.assertTrue(driver.findElement(By.cssSelector(".float\\-cart__content")).isDisplayed());

        // Check the product inside the cart is same as of the main page
        String productOnCartText = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div[2]/div[2]/div/div[3]/p[1]")).getText();
        Assert.assertEquals(productOnScreenText, productOnCartText);
    }

}

