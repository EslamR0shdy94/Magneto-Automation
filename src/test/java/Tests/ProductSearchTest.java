package Tests;

import Pages.HomePage;
import org.testng.Assert;
import utils.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductSearchTest {
    @BeforeMethod
    public void setup() {
        WebDriverManager.getDriver().get("https://magento.softwaretestingboard.com/");
    }

    @Test
    public void testValidProductSearch() {
        HomePage homePage = new HomePage(WebDriverManager.getDriver());

        // 1. Search for "Hero Hoodie"
        homePage.searchForProduct("Hero Hoodie");

        // 2. Verify the product appears
        homePage.verifyProductExists("Hero Hoodie");
        boolean isProductFound = homePage.verifyProductExists("Hero Hoodie");
        Assert.assertTrue(isProductFound, "Expected product 'Hero Hoodie' was not found!");
    }
    @Test
    public void testInvalidProductSearch() {
        HomePage homePage = new HomePage(WebDriverManager.getDriver());
        homePage.searchForProduct("تي شيرت");
        Assert.assertTrue(
                homePage.isNoResultsMessageDisplayed(),
                "Error message should appear for invalid search"
        );
    }
    @AfterMethod
    public void tearDown() {
        WebDriverManager.quitDriver();
    }
}