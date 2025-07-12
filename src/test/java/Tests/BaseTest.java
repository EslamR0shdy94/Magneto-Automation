package Tests;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.WebDriverManager;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = WebDriverManager.getDriver();
    }

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @AfterMethod
    public void captureFailureScreenshot(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(driver);
        }
    }
    @AfterClass
    public void tearDown() {
        WebDriverManager.quitDriver();
    }
}
