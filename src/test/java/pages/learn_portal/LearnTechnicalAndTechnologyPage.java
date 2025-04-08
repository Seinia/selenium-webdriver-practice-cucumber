package pages.learn_portal;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base.BasePage;

import java.util.List;

@Slf4j
public class LearnTechnicalAndTechnologyPage extends BasePage {

    @FindBy(xpath = "//div[@role='option'][.//div[text()='English']]//div[@class='uui-checkbox']")
    private WebElement englishCheckBox;

    @FindBy(xpath = "//div[@role='option'][.//div[text()='More than 20 hours']]//div[@class='uui-checkbox']")
    private WebElement moreThanTwentyHoursCheckBox;

    @FindBy(xpath = "//div[@role='option'][.//div[text()='Intermediate']]//div[@class='uui-checkbox']")
    private WebElement intermediateCheckBox;

    @FindBy(xpath = "//div[@role='option'][.//div[text()='EPAM']]//div[@class='uui-checkbox']")
    private WebElement epamCheckBox;

    @FindBy(partialLinkText = "C# Advanced")
    private WebElement dotNetCourseCard;

    @FindBy(xpath = "//div[text()='Technical and Technology']")
    private WebElement technicalAndTechnologyPageText;

    @FindBy(css = ".Block_flex__FM3S9.Block_x__aZpbI.ExploreFilterChips_chip__OAl2H")
    private List<WebElement> listOfFilters;

    public LearnTechnicalAndTechnologyPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LearnTechnicalAndTechnologyPage clickEnglishCheckBox(){
        scrollToElementAndClick(englishCheckBox);
        log.info("Clicked english check box on the left filter menu");
        return this;
    }

    public LearnTechnicalAndTechnologyPage clickMoreThanTwentyHoursCheckBox(){
        scrollToElementAndClick(moreThanTwentyHoursCheckBox);
        log.info("Click more than 20 hours check box on the left filter menu");
        return this;
    }

    public LearnTechnicalAndTechnologyPage clickIntermediateCheckBox(){
        scrollToElementAndClick(intermediateCheckBox);
        log.info("Click intermediate check box on the left filter menu");
        return this;
    }

    public LearnTechnicalAndTechnologyPage clickEpamCheckBox(){
        scrollToElementAndClick(epamCheckBox);
        log.info("Click EPAM check box on the left filter menu");
        return this;
    }

    public LearnDotNetCoursePage clickDotNetCourseCard(){
        scrollToElementAndClick(dotNetCourseCard);
        log.info("Click necessary course card after filtering");
        return new LearnDotNetCoursePage(driver);
    }

    private void scrollToElementAndClick(WebElement webElement){
        scrollToElement(webElement);
        clickElement(webElement);
    }

    public int getListOfFiltersSize(){
        return listOfFilters.size();
    }

    public String getTechnicalAndTechnologyPageText(){
        log.debug("Got text for verification of Learn Technical and Technology Page");
        return getTextFromElement(technicalAndTechnologyPageText);
    }
}
