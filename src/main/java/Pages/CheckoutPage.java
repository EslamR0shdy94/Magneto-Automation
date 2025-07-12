package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverManager;

import java.time.Duration;

public class CheckoutPage extends BasePage {
    private final WebDriverWait wait;
    WebDriver driver;

    // ===== SHIPPING INFORMATION ELEMENTS =====
    @FindBy(name = "firstname")
    private WebElement firstNameField;

    @FindBy(name = "lastname")
    private WebElement lastNameField;

    @FindBy(name = "street[0]")
    private WebElement streetField;

    @FindBy(name = "city")
    private WebElement cityField;

    @FindBy(name = "region_id")
    private WebElement stateDropdown;

    @FindBy(name = "country_id")
    private WebElement countryDropdown;
//    selectCountry
    @FindBy(name = "postcode")
    private WebElement zipField;

    @FindBy(name = "telephone")
    private WebElement phoneField;

    @FindBy(css = "button.continue")
    private WebElement shippingContinueButton;

    // ===== PAYMENT SECTION ELEMENTS =====
    @FindBy(id = "customer-email")
    private WebElement emailField;

    @FindBy(id = "customer-email-error")
    private WebElement emailErrorMessage;

    @FindBy(css = "input.radio[value='flatrate_flatrate']")
    private WebElement flatRateRadio;

    @FindBy(xpath = "//td[@class='col col-price']//span[@class='price']")
    private WebElement flatRatePrice;

    // Table Rate shipping radio
    @FindBy(css = "input.radio[value='tablerate_bestway']")
    private WebElement tableRateBestWayRadio;

    @FindBy(css = "tr.row .col.col-price .price .price")
    private WebElement tableRatePrice;

    @FindBy(css = "span[data-bind=\"i18n: 'Next'\"]")
    private WebElement nextButton;

    @FindBy(id = "billing-address-same-as-shipping-checkmo")
    private WebElement sameAsShippingCheckbox;

    @FindBy(xpath = "//span[contains(@data-bind, 'Place Order')]")
    private WebElement placeOrderButton;

    // ===== ERROR MESSAGES =====
    @FindBy(css = ".payment-method._active .field-error")
    private WebElement paymentError;

    @FindBy(css = ".message-error.error.message")
    private WebElement generalErrorMessage;


        // ==================== CONSTRUCTOR ====================
    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);

    }

    // ==================== SHIPPING METHODS ====================
    public void fillShippingInformation(String email,String firstName, String lastName, String street,
                                        String city,String country, String zip, String phone, String state) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.clear();
        emailField.sendKeys(email);

        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        lastNameField.clear();
        lastNameField.sendKeys(lastName);

        streetField.clear();
        streetField.sendKeys(street);

        cityField.clear();
        cityField.sendKeys(city);

        zipField.clear();
        zipField.sendKeys(zip);

        phoneField.clear();
        phoneField.sendKeys(phone);

        selectState(state);

        selectCountry(country);

        wait.until(ExpectedConditions.visibilityOf(countryDropdown));
        new Select(countryDropdown).selectByVisibleText(country);

        wait.until(ExpectedConditions.visibilityOf(stateDropdown));
        new Select(stateDropdown).selectByVisibleText(state);
    }

    public boolean isEmailErrorDisplayed() {
        return isDisplayed(emailErrorMessage);
    }

    public void selectFlatRateShipping() {
        wait.until(ExpectedConditions.elementToBeClickable(flatRateRadio));
        if (!flatRateRadio.isSelected()) {
            flatRateRadio.click();
        }
    }
    public void selectAgreementCheckbox() {
        wait.until(ExpectedConditions.elementToBeClickable(sameAsShippingCheckbox));
        if (!sameAsShippingCheckbox.isSelected()) {
            sameAsShippingCheckbox.click();
        }
    }

    public void clickPlaceOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
        placeOrderButton.click();
    }

    public void continueToPayment() {
        wait.until(ExpectedConditions.elementToBeClickable(shippingContinueButton));
        shippingContinueButton.click();
    }

    // ==================== PAYMENT METHODS ====================
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.clear();
        emailField.sendKeys(email);
    }
    public void selectState(String stateName) {
        wait.until(ExpectedConditions.visibilityOf(stateDropdown));
        Select select = new Select(stateDropdown);
        select.selectByVisibleText(stateName);
    }
    public void selectCountry(String countryeName) {
        wait.until(ExpectedConditions.visibilityOf(countryDropdown));
        Select select = new Select(countryDropdown);
        select.selectByVisibleText(countryeName);
    }
    public void selectShippingMethod(String method) {
        if ("flat rate".equalsIgnoreCase(method)) {
            wait.until(ExpectedConditions.elementToBeClickable(flatRateRadio));
            flatRateRadio.click();
        } else if ("table rate".equalsIgnoreCase(method)) {
            wait.until(ExpectedConditions.elementToBeClickable(tableRateBestWayRadio));
            tableRateBestWayRadio.click();
        } else {
            throw new IllegalArgumentException("Invalid shipping method: " + method);
        }
    }

    public void nextpage() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        nextButton.click();
        scrollBy(0, 400);
        WebDriverManager.getDriver().get("https://magento.softwaretestingboard.com/checkout/#payment");
    }
    // ==================== UTILITY ====================
    private boolean isDisplayed(WebElement element) {
        scrollBy(0, 400);
        try {
            return element != null && element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
