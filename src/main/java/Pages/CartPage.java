package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(css = "tr.item-info")
    private List<WebElement> cartItems;

    @FindBy(css = ".col.price .price")
    private WebElement itemPrice;

    @FindBy(css = ".col.subtotal .price")
    private WebElement subtotalPrice;

    @FindBy(css = ".action-delete")
    private WebElement removeItemButton;

    @FindBy(xpath = "//span[text()='Proceed to Checkout']")
    public WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isItemInCart(String itemName) {
        return cartItems.stream()
                .anyMatch(item -> item.getText().contains(itemName));
    }

    public String getItemPrice() {
        return itemPrice.getText();
    }

    public int getCartItemCount() {
        return cartItems.size();
    }

    public void proceedToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        checkoutButton.click();
    }
    public void clearCart() {
        if (getCartItemCount() > 0) {
            removeItemButton.click();
            try {
                Thread.sleep(1000); // Simple wait for cart update
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}