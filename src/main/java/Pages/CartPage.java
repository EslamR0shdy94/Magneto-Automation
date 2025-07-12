//package Pages;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import java.time.Duration;
//import java.util.List;
//
//public class CartPage extends BasePage {
//
//    @FindBy(css = "tr.item-info")
//    private List<WebElement> cartItems;
//
//    @FindBy(css = ".col.price .price")
//    private WebElement itemPrice;
//
//    @FindBy(css = ".col.subtotal .price")
//    private WebElement subtotalPrice;
//
//    @FindBy(css = ".action-delete")
//    private WebElement removeItemButton;
//
//    @FindBy(xpath = "//span[text()='Proceed to Checkout']")
//    public WebElement checkoutButton;
//
//    @FindBy(css = "span.count")
//    private WebElement cartCounter;
//
//    public CartPage(WebDriver driver) {
//        super(driver);
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//        scrollBy(0, 400); // Scroll to make elements visible if needed
//    }
//
//    public boolean isItemInCart(String itemName) {
//        return cartItems.stream().anyMatch(item -> item.getText().contains(itemName));
//    }
//
//    public String getItemPrice() {
//        return itemPrice.getText();
//    }
//
//    public int getCartItemCount() {
//        return cartItems.size(); // Or use cartCounter.getText() if needed
//    }
//
//    public void proceedToCheckout() {
//        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
//    }
//
//    public void clearCart() {
//        if (getCartItemCount() > 0) {
//            removeItemButton.click();
//
//            // Wait until cart item is removed (i.e., item rows disappear)
//            wait.withTimeout(Duration.ofSeconds(5))
//                    .until(ExpectedConditions.numberOfElementsToBeLessThan(
//                            org.openqa.selenium.By.cssSelector("tr.item-info"),
//                            getCartItemCount()
//                    ));
//        }
//    }
//}
package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(css = "tr.item-info")
    private List<WebElement> cartItems;

    @FindBy(css = ".col.price .price")
    private WebElement itemPrice;

    @FindBy(css = "a.action-delete")
    private WebElement removeItemButton;

    @FindBy(xpath = "//span[text()='Proceed to Checkout']")
    public WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        scrollBy(0, 400); // Scroll for visibility
    }

    public boolean isItemInCart(String itemName) {
        return cartItems.stream().anyMatch(item -> item.getText().contains(itemName));
    }

    public String getItemPrice() {
        return itemPrice.getText();
    }

    public int getCartItemCount() {
        return cartItems.size(); // Or read from cartCounter if needed
    }

    public boolean isCartEmpty() {
        return cartItems.isEmpty();
    }

    public void proceedToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

    public void clearCart() {
        if (!isCartEmpty()) {
            removeItemButton.click();
            wait.until(ExpectedConditions.numberOfElementsToBe(
                    By.cssSelector("tr.item-info"), 0
            ));
        }
    }
}
