package Tests;

import Pages.HomePage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductSearchTest extends BaseTest {

    @Epic("search Module")
    @Feature("search Feature")
    @Story("search product")
    @Description("Verify that user can search product and invalid product")
    @Test
    public void testValidProductSearch() {
        driver.get("https://magento.softwaretestingboard.com/");
        HomePage homePage = new HomePage(driver);

        homePage.searchForProduct("Hero Hoodie");
        boolean isProductFound = homePage.verifyProductExists("Hero Hoodie");
        Assert.assertTrue(isProductFound);
    }

    @Test
    public void testInvalidProductSearch() {
        driver.get("https://magento.softwaretestingboard.com/");
        HomePage homePage = new HomePage(driver);

        homePage.searchForProduct("تي شيرت");
        Assert.assertTrue(homePage.isNoResultsMessageDisplayed());
    }
}
