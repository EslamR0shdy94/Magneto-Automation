package Tests;

import Pages.*;
import utils.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AddToCartTest {
    private static final String PRODUCT_URL = "https://magento.softwaretestingboard.com/hero-hoodie.html";
    private static final String EXPECTED_PRICE = "$54.00";

    @BeforeClass
    public void setup() {
        WebDriverManager.getDriver().get(PRODUCT_URL);
    }

    @Test
    public void testAddToCartAndValidation() {
        ProductPage productPage = new ProductPage(WebDriverManager.getDriver());
        HomePage homePage = new HomePage(WebDriverManager.getDriver());

        // 1. Select options and add to cart
        productPage.selectSizeAndColor();
        productPage.addToCart();

        // 2. Verify cart counter
        Assert.assertEquals(productPage.getCartItemCount(), 1, "Cart count should be 1");

        // 3. Navigate to cart and verify item
        productPage.navigateToCart();
        CartPage cartPage = new CartPage(WebDriverManager.getDriver());

        assertTrue(cartPage.isItemInCart("Hero Hoodie"), "Item should be in cart");
        Assert.assertEquals(cartPage.getItemPrice(), EXPECTED_PRICE, "Price should match");
    }

    @Test(dependsOnMethods = "testAddToCartAndValidation")
    public void testCartPersistence() {
        // We are using same browser session, no need to quit or relaunch
        CartPage cartPage = new CartPage(WebDriverManager.getDriver());
        WebDriverManager.getDriver().get("https://magento.softwaretestingboard.com/checkout/cart/");

        assertTrue(cartPage.isItemInCart("Hero Hoodie"), "Item should persist in cart");
        Assert.assertEquals(cartPage.getItemPrice(), EXPECTED_PRICE, "Price should persist");
    }

    @AfterClass
    public void tearDown() {
        // Clear cart after all tests done
        WebDriverManager.getDriver().get("https://magento.softwaretestingboard.com/checkout/cart/");
        new CartPage(WebDriverManager.getDriver()).clearCart();
        WebDriverManager.quitDriver();
    }
}
