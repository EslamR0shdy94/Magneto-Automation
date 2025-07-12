package Tests;

import Pages.*;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.Assert;
import static org.testng.Assert.assertTrue;

public class CheckoutTest extends BaseTest {
    private static final String PRODUCT_URL = "https://magento.softwaretestingboard.com/hero-hoodie.html";

    @Epic("check out Module")
    @Feature("check out Feature")
    @Story("check out")
    @Description("Verify that user can check out after add product to cart")
    @Test
    public void testCheckoutProcess() {
        driver.get(PRODUCT_URL);
        ProductPage productPage = new ProductPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        productPage.selectSizeAndColor();
        productPage.addToCart();
        productPage.navigateToCart();

        CartPage cartPage = new CartPage(driver);
        assertTrue(cartPage.isItemInCart("Hero Hoodie"));

        cartPage.proceedToCheckout();

        checkoutPage.fillShippingInformation(
                "invalid-email", "John", "Doe", "123 Test St.", "Test City",
                "United States", "12345", "1234567890", "California"
        );

        assertTrue(checkoutPage.isEmailErrorDisplayed());

        checkoutPage.enterEmail("eslam@example.com");
        checkoutPage.continueToPayment();

        checkoutPage.fillShippingInformation(
                "eslam@example.com", "eslam", "roshdy", "123 Main St", "arizona city",
                "United States", "10001", "1234567890", "Arizona"
        );

        checkoutPage.selectFlatRateShipping();
        checkoutPage.selectShippingMethod("flat rate");
        checkoutPage.nextpage();
        checkoutPage.selectAgreementCheckbox();
        checkoutPage.clickPlaceOrder();

//        Assert.assertFalse(checkoutPage.isPaymentErrorDisplayed());
//        assertTrue(driver.getCurrentUrl().contains("/checkout/onepage/success/"));
    }
}
