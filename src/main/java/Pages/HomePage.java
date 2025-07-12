package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {
    @FindBy(id = "search")
    private WebElement searchBar;

    @FindBy(css = "button.action.search")
    private WebElement searchButton;

    @FindBy(css = ".product-item-link")
    private WebElement firstProductName;

    @FindBy(css = ".message.notice")
    private WebElement noResultsMessage;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void searchForProduct(String productName) {
        searchBar.clear();
        searchBar.sendKeys(productName);
        searchButton.click();
    }
    // For valid search
    public boolean verifyProductExists(String expectedProduct) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(firstProductName));
            return firstProductName.getText().equals(expectedProduct);
        } catch (Exception e) {
            return false;
        }
    }
    // For invalid search
    public boolean isNoResultsMessageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(noResultsMessage));
            return noResultsMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}