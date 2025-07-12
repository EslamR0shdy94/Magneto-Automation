package Pages;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
    @FindBy(css = "button.action-close")
    private  WebElement closeZipperButton;
    public ProductPage(WebDriver driver) {
        super(driver);
    }
    public void selectSizeAndColor() {
        sizeM.click();
        colorBlack.click();
    }

    public void addToCart() {
//        wait.until(ExpectedConditions.visibilityOf(successMessage));
        JavascriptExecutor js = (JavascriptExecutor) driver;
         js.executeScript("arguments[0].scrollIntoView({block: 'center'});", addToCartButton);
        addToCartButton.click();
    }

    public int getCartItemCount() {
        System.out.println("Value = '" + cartCounter + "'");
        return Integer.parseInt(cartCounter.getText());
    }

    public void navigateToCart() {
        driver.get("https://magento.softwaretestingboard.com/checkout/cart/");
    }
}