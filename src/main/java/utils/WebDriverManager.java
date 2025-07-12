package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class WebDriverManager {
    private static WebDriver driver;

    // Get the existing driver or create a new one if null
    public static WebDriver getDriver() {
        if (driver == null) {
            System.out.println("Initializing new ChromeDriver session...");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }

    // Quit the driver and clean up
    public static void quitDriver() {
        if (driver != null) {
            System.out.println("Closing WebDriver session.");
            driver.quit();
            driver = null;
        }
    }

//    // Optional helper: navigate to a URL
//    public static void navigateTo(String url) {
//        getDriver().get(url);
//    }
//
//    // Optional helper: clear cookies between tests
//    public static void clearCookies() {
//        if (driver != null) {
//            driver.manage().deleteAllCookies();
//        }
//    }
}