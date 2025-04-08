package pages.epam_portal;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;

import java.time.Duration;

@Slf4j
public class EpamJobListPage extends BasePage {

    @FindBy(id = "new_form_job_search-keyword")
    private WebElement skillsField;

    @FindBy(className = "select2-selection__rendered")
    private WebElement locationTextBox;

    @FindBy(xpath = "//li[text()='Київ']")
    private WebElement locationTextBoxOpened;

    @FindBy(className = "default-label")
    private WebElement specialisationTextBox;

    @FindBy(xpath = "//span[text()='Software, System, and Test Engineering']")
    private WebElement specialisationCheckBox;

    @FindBy(xpath = "//a[@class='search-result__item-name']")
    private WebElement jobCard;

    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    private WebElement acceptCookieButton;

    @FindBy(xpath = "//button[@class='recruiting-search__submit button-ui ']")
    private WebElement searchButton;

    public EpamJobListPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public EpamJobListPage acceptCookie() {
        clickElement(acceptCookieButton);
        log.info("Accepted cookies for the EPAM home page");
        return this;
    }

    public EpamJobListPage inputSkillsField(String text){
        clearBAndInputText(skillsField, text);
        log.info("Filled skills in the skills input on filter menu");
        return this;
    }

    public EpamJobListPage clickSearchButton(){
        clickElement(searchButton);
        log.info("Search button was clicked");
        return this;
    }

    public EpamJobListPage inputLocationTextBox(){
        clickElement(locationTextBox);
        clickElement(locationTextBoxOpened);
        log.info("Filled location in the location input field on filter menu");
        return this;
    }

    public EpamJobListPage clickSpecialisationTextBox(){
        clickElement(specialisationTextBox);
        log.info("Clicked specialisation text box on filter menu");
        return this;
    }

    public EpamJobListPage clickSpecialisationCheckBox(){
        clickElement(specialisationCheckBox);
        log.info("Clicked specialisation check box in drop down on filter menu");
        return this;
    }

    public String getJobCardText(String skill) {
        waitForElementToContainText(jobCard, skill, 500);
        log.debug("Got text of job card from EPAM Job Details Page");
        return getTextFromElement(jobCard);
    }

    public EpamJobDetailsPage clickJobCard(String skill) {
        waitForElementToContainText(jobCard, skill, 500);
        clickElement(jobCard);
        log.info("Clicked necessary job card after filtering");
        return new EpamJobDetailsPage(driver);
    }

    private void waitForElementToContainText(WebElement element, String text, int duration) {
        new WebDriverWait(driver, waitTimeout)
                .withMessage("Could not find an item in drop down")
                .pollingEvery(Duration.ofMillis(duration))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }

}
