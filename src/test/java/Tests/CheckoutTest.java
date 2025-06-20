package Tests;

import Pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.WebDriverManager;
import org.testng.Assert;
import static org.testng.Assert.assertTrue;

public class CheckoutTest {
    private WebDriver driver;
    private static final String PRODUCT_URL = "https://magento.softwaretestingboard.com/hero-hoodie.html";

    @BeforeClass
    public void setup() {
        driver = WebDriverManager.getDriver();
    }

    @Test
    public void testCheckoutProcess() {
        // 1) Go to product page and add to cart
        driver.get(PRODUCT_URL);
        ProductPage productPage = new ProductPage(driver);
        productPage.selectSizeAndColor();
        productPage.addToCart();

        // 2) Navigate to cart page
        productPage.navigateToCart();
        CartPage cartPage = new CartPage(driver);
        assertTrue(cartPage.isItemInCart("Hero Hoodie"), "Product should be in cart");
        // 3) Proceed to checkout from cart
        cartPage.proceedToCheckout();
        // 4) Fill shipping information
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillShippingInformation(
                "invalid-email",
                "John", "Doe", "123 Test St.", "Test City",
                "United States", "12345", "1234567890",
                "California"
        );

        assertTrue(checkoutPage.isEmailErrorDisplayed(),
                "Expected email validation error to be displayed.");
        System.out.println("Email Error Message: " + checkoutPage.getEmailErrorMessage());

        checkoutPage.enterEmail("eslam@example.com");

        checkoutPage.continueToPayment();

        checkoutPage.fillShippingInformation(
                "eslam@example.com", "eslam", "roshdy", "123 Main St", "arizona city","United States", "10001", "1234567890", "Arizona");

        checkoutPage.selectFlatRateShipping();
        checkoutPage.selectShippingMethod("flat rate");
        // 6) Place order
        checkoutPage.nextpage();

        checkoutPage.selectAgreementCheckbox();

        checkoutPage.clickPlaceOrder();
        // 7) Validate success or errors
        Assert.assertFalse(
                checkoutPage.isPaymentErrorDisplayed(),
                "There should be no payment error"
        );

    }
    @AfterClass
    public void checkurl(){
        assertTrue(
                driver.getCurrentUrl().contains("/checkout/onepage/success/"),
                "User should be on success page");
        driver.quit();
    }
}