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
public class CampusCoursePage extends BasePage {

    @FindBy(xpath = "(//div[text()='Register'])[1]")
    private WebElement registerButton;

    public CampusCoursePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CampusLoginPage clickRegisterButton(){
        clickElement(registerButton);
        log.info("Clicked registration button on center of course page");
        return new CampusLoginPage(driver);
    }
}
