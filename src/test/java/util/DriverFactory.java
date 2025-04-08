package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

@Slf4j
public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            WebDriver webDriver;
            switch (System.getProperty("browser", "chrome")) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    webDriver = new FirefoxDriver();
                    log.debug("Firefox driver initialized for the test");
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    webDriver = new EdgeDriver();
                    log.debug("Edge driver initialized for the test");
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    webDriver = new SafariDriver();
                    log.debug("Safari driver initialized for the test");
                    break;
                case "ie":
                    WebDriverManager.iedriver().setup();
                    webDriver = new InternetExplorerDriver();
                    log.debug("Internet Explorer driver initialized for the test");
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    webDriver = new ChromeDriver();
                    log.debug("Chrome driver initialized for the test");
            }
            webDriver.manage().window().maximize();
            driver.set(webDriver);
        }
        log.debug("Driver was initialized before");
        return driver.get();
    }

    public static void closeDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            log.debug("Driver was closed");
            driver.remove();
        }
    }
}