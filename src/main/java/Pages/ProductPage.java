package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//div[@aria-label='M']") // Size M
    private WebElement sizeM;

    @FindBy(css = "div[option-label='Black']") // Color Black
    private WebElement colorBlack;

    @FindBy(id = "product-addtocart-button")
    private WebElement addToCartButton;

    @FindBy(css = ".message-success a[href='https://magento.softwaretestingboard.com/checkout/cart/']")
    private WebElement successMessage;

    @FindBy(css = ".counter-number")
    private WebElement cartCounter;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void selectSizeAndColor() {
        sizeM.click();
        colorBlack.click();
    }

    public void addToCart() {
        addToCartButton.click();
        wait.until(ExpectedConditions.visibilityOf(successMessage));
    }

    public int getCartItemCount() {
        return Integer.parseInt(cartCounter.getText());
    }

    public void navigateToCart() {
        driver.get("https://magento.softwaretestingboard.com/checkout/cart/");
    }
}