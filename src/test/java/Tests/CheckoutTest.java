package Tests;

import Pages.*;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

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
        productPage.scrollToAddToCart();
        productPage.selectSizeAndColor();
        productPage.addToCart();
        productPage.navigateToCart();
        CartPage cartPage = new CartPage(driver);
        assertTrue(cartPage.isItemInCart("Hero Hoodie"));

        cartPage.proceedToCheckout();
// First attempt with invalid email (should fail)
        checkoutPage.fillShippingInformation(
                "invalid-email", "John", "Doe", "123 Test St.", "Test City",
                "United States", "12345", "1234567890", "California"
        );

        assertTrue(checkoutPage.isEmailErrorDisplayed());

        checkoutPage.enterEmail("eslam@example.com");
        checkoutPage.continueToPayment();

// Second attempt with valid details (should pass)
        checkoutPage.fillShippingInformation(
                "eslam@example.com", "eslam", "roshdy", "123 Main St", "arizona city",
                "United States", "10001", "1234567890", "Arizona"
        );

        checkoutPage.selectFlatRateShipping();
        checkoutPage.selectShippingMethod("flat rate");
        checkoutPage.nextpage();
        checkoutPage.selectAgreementCheckbox();
        checkoutPage.clickPlaceOrder();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[contains(text(), 'Thank you for your purchase!')]")
            ));

            assertTrue(successMessage.isDisplayed(), "Success message should be displayed");
            System.out.println("Order completed successfully");

            System.out.println("Current URL: " + driver.getCurrentUrl());
            assertTrue(successMessage.isDisplayed(), "Success message should be displayed");
            assertTrue(driver.getCurrentUrl().contains("checkout/onepage/success"),
                    "User should be on success page");
            System.out.println("Order completed successfully");
        } catch (TimeoutException e) {
            throw new AssertionError("User did not reach the success page in time", e);
        }
    }
}
