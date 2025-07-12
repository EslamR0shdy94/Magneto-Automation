package Tests;

import Pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.*;
import static org.testng.Assert.assertTrue;

public class AddToCartTest extends BaseTest {
    private static final String PRODUCT_URL = "https://magento.softwaretestingboard.com/hero-hoodie.html";
    private static final String EXPECTED_PRICE = "$54.00";

    @Epic("Add To cart Module")
    @Feature("Cart Feature")
    @Story("add to cart")
    @Description("Verify that user can add product to cart and delete it")
    @Test
    public void testAddToCartAndValidation() {
        driver.get(PRODUCT_URL);
        ProductPage productPage = new ProductPage(driver);

        productPage.selectSizeAndColor();
        productPage.addToCart();

        Assert.assertEquals(productPage.getCartItemCount(), 1);
        productPage.navigateToCart();

        CartPage cartPage = new CartPage(driver);
        assertTrue(cartPage.isItemInCart("Hero Hoodie"));
        Assert.assertEquals(cartPage.getItemPrice(), EXPECTED_PRICE);
    }

    @Test(dependsOnMethods = "testAddToCartAndValidation")
    public void testCartPersistence() {
        driver.get("https://magento.softwaretestingboard.com/checkout/cart/");
        CartPage cartPage = new CartPage(driver);

        assertTrue(cartPage.isItemInCart("Hero Hoodie"));
        Assert.assertEquals(cartPage.getItemPrice(), EXPECTED_PRICE);
    }
}
