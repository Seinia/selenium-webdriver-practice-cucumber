package pages.learn_portal;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;
import pages.campus_portal.CampusHomePage;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class LearnHomePage extends BasePage {

    private final String homepageUrl = "https://learn.epam.com/start";

    @FindBy(linkText = "Catalog")
    private WebElement catalogButton;

    @FindBy(linkText = "Campus")
    private WebElement campusButton;

    @FindBy(xpath = "//div[text()='Home']")
    private WebElement homePageText;

    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    private WebElement acceptCookieButton;


    public LearnHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LearnHomePage openPage() {
        driver.get(homepageUrl);
        return this;
    }

    public LearnCatalogPage clickCatalogButton(){
        clickElement(catalogButton);
        log.info("Clicked catalog button on the top of left navigation menu");
        return new LearnCatalogPage(driver);
    }

    public LearnHomePage acceptCookie(){
        clickElement(acceptCookieButton);
        log.info("Accept cookies for the Learn home page");
        return this;
    }

    public String getHomePageText(){
        log.debug("Got text for verification of Learn Home Page");
        return getTextFromElement(homePageText);
    }

    public CampusHomePage clickCampusButton(){
        new WebDriverWait(driver, waitTimeout)
                .until(ExpectedConditions.visibilityOf(campusButton));
        clickElement(campusButton);
        log.info("Clicked campus button on the bottom of left navigation menu");
        return new CampusHomePage(driver);
    }

    public void switchToNewTab() {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
        log.info("Switched to a new tab from Learn Home Page");
    }

}
