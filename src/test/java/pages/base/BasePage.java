package pages.base;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Slf4j
public abstract class BasePage {

    protected WebDriver driver;

    protected final Duration waitTimeout = Duration.ofSeconds(10);

    protected BasePage(WebDriver driver){
        this.driver = driver;
    }

    protected void clickElement(WebElement element){
        try {
            new WebDriverWait(driver, waitTimeout).
                    until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (ElementClickInterceptedException e) {
            log.warn("Element click intercepted, trying JavaScript click instead.");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    protected String getTextFromElement(WebElement element){
        return new WebDriverWait(driver, waitTimeout)
                .until(ExpectedConditions.visibilityOf(element)).getText();
    }

    protected void inputText(WebElement element, String text){
        new WebDriverWait(driver, waitTimeout)
                .until(ExpectedConditions.elementToBeClickable(element)).sendKeys(text);
    }

    protected void clearBAndInputText(WebElement element, String text){
        new WebDriverWait(driver, waitTimeout)
                .until(ExpectedConditions.elementToBeClickable(element)).clear();
        element.sendKeys(text);
    }

    protected void scrollToElement(WebElement webElement, String argument) {
        new WebDriverWait(driver, waitTimeout)
                .until(ExpectedConditions.visibilityOf(webElement));
        ((JavascriptExecutor) driver).executeScript(argument, webElement);
    }

    protected void scrollToElement(WebElement webElement){
        scrollToElement(webElement, "arguments[0].scrollIntoView(true);");
    }
}