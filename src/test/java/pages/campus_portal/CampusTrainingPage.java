package pages.campus_portal;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;

import java.time.Duration;
import java.util.List;

@Slf4j
public class CampusTrainingPage extends BasePage {

    @FindBy(xpath =
            "//div[contains(@class, 'FMk1Jo') and contains(@class, 'uui-size-36')][text()='Locations']"
    )
    private WebElement locationsDropDown;

    @FindBy(xpath =
            "//div[contains(@class, 'FMk1Jo') and contains(@class, 'uui-size-36')][text()='Skills']"
    )
    private WebElement skillsDropDown;

    @FindBy(css = "input[type='search']")
    private WebElement searchField;

    @FindBy(xpath = "//h1[text()='Training programs']")
    private WebElement trainingPageText;

    @FindBy(xpath = "//div[text()='Introduction to Automated Testing in JavaScript']")
    private WebElement courseCard;

    @FindBy(className = "yByDq3")
    private List<WebElement> dropDownCheckBox;

    public CampusTrainingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CampusTrainingPage clickLocationsDropDown(){
        clickElement(locationsDropDown);
        log.info("Clicked drop down for location on the filter menu");
        return this;
    }

    public CampusTrainingPage clickSkillsDropDown(){
        clickElement(skillsDropDown);
        log.info("Clicked drop down for skills on the filter menu");
        return this;
    }

    public CampusCoursePage clickCourseCard(){
        clickElement(courseCard);
        log.info("Clicked necessary course card after filtering");
        return new CampusCoursePage(driver);
    }

    public String getCourseCardText(){
        log.debug("Got text for verification of course card");
        return getTextFromElement(courseCard);
    }

    public CampusTrainingPage inputSearchField(String text){
        inputText(searchField, text);
        log.info("Filled text to input field to filter courses");
        return this;
    }

    public CampusTrainingPage clickDropDownCheckBox(){
        new WebDriverWait(driver, waitTimeout)
                .withMessage("Could not find an item in drop down")
                .pollingEvery(Duration.ofMillis(50))
                .ignoring(StaleElementReferenceException.class)
                .until(driver -> dropDownCheckBox.size() == 1);
        dropDownCheckBox.get(0).click();
        log.info("Clicked first item from drop down menu");
        return this;
    }

    public String getTrainingPageText(){
        log.debug("Got text for verification of Campus Training Page");
        return getTextFromElement(trainingPageText);
    }




}
