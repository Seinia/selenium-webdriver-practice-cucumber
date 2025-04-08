package pages.epam_portal;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base.BasePage;

@Slf4j
public class EpamJobDetailsPage extends BasePage {

    @FindBy(css = "span[data-iso2-country-code='UA']")
    private WebElement locationText;

    @FindBy(xpath = "//header[@class='recruiting-page__header']//h1")
    private WebElement jobTitle;

    public EpamJobDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getLocationText(){
        log.debug("Got text for verification of location from EPAM Careers Page");
        return getTextFromElement(locationText);
    }

    public String getJobTitleText(){
        log.debug("Got text for verification of job title from EPAM Job Details Page");
        return getTextFromElement(jobTitle);
    }
}
