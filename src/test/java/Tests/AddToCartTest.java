package Tests;

import Pages.CartPage;
import Pages.ProductPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

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
        productPage.scrollToAddToCart();
        productPage.selectSizeAndColor();
        productPage.addToCart();
        productPage.navigateToCart();
        CartPage cartPage = new CartPage(driver);
        assertTrue(cartPage.isItemInCart("Hero Hoodie"), "Product should appear in the cart");
        Assert.assertEquals(cartPage.getItemPrice(), EXPECTED_PRICE, "Product price should match expected");
    }

    @Test(dependsOnMethods = "testAddToCartAndValidation")
    public void testCartClear() {
        driver.get("https://magento.softwaretestingboard.com/checkout/cart/");
        CartPage cartPage = new CartPage(driver);
        cartPage.clearCart();
        assertTrue(cartPage.isCartEmpty(), "Cart should be empty after clearing");
    }

}

