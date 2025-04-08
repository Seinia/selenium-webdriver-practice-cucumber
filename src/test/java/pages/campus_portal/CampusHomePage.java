package pages.campus_portal;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base.BasePage;


@Slf4j
public class CampusHomePage extends BasePage {

    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    private WebElement acceptCookieButton;

    @FindBy(xpath = "//div[text()='Find a program']")
    private WebElement findAProgramButton;

    @FindBy(xpath = "//div[text()='Start your']")
    private WebElement homePageText;

    public CampusHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CampusHomePage acceptCookie(){
        clickElement(acceptCookieButton);
        log.info("Accepted cookies for Campus home page");
        return this;
    }

    public String getHomePageText(){
        return getTextFromElement(homePageText);
    }

    public CampusTrainingPage clickFindAProgramButton(){
        clickElement(findAProgramButton);
        log.info("Clicked \"Find a program\" button on the center of the page");
        return new CampusTrainingPage(driver);
    }


}
