package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        scrollBy(0, 300); // Optional scroll on page load
    }

    // ========== ELEMENTS ==========
    @FindBy(id = "option-label-size-143-item-168")
    private WebElement sizeOption;

    @FindBy(css = "div.swatch-option.color")
    private WebElement colorOption;

    @FindBy(id = "product-addtocart-button")
    private WebElement addToCartButton;

    // ========== ACTIONS ==========

    public void selectSizeAndColor() {
        wait.until(ExpectedConditions.elementToBeClickable(sizeOption)).click();
        wait.until(ExpectedConditions.elementToBeClickable(colorOption)).click();
    }

    public void scrollToAddToCart() {
        scrollToElement(addToCartButton);
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }

    public void navigateToCart() {
        driver.get("https://magento.softwaretestingboard.com/checkout/cart/");
        driver.navigate().refresh();
    }
}
