package pages.learn_portal;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;

@Slf4j
public class LearnDotNetCoursePage extends BasePage {

    @FindBy(xpath = "//div[text()='ENG']")
    private WebElement languageText;

    @FindBy(xpath = "//div[text()='145 hr 41 min']")
    private WebElement estimatedEffortsText;

    public LearnDotNetCoursePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getLanguageText(){
        log.debug("Got language text for verification of course from Learn Catalog Page");
        return getTextFromElement(languageText);
    }

    public String getEstimatedEffortsText(){
        log.debug("Got estimated efforts text text for verification of course from Learn Catalog Page");
        return getTextFromElement(estimatedEffortsText);
    }

}
