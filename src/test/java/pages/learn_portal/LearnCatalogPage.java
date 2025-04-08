package pages.learn_portal;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base.BasePage;

@Slf4j
public class LearnCatalogPage extends BasePage {

    @FindBy(xpath = "//div[text()='Technical and Technology']")
    private WebElement technicalAndTechnologyCard;

    @FindBy(xpath = "//div[text()='Catalog']")
    private WebElement catalogPageText;

    public LearnCatalogPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getCatalogPageText(){
        log.debug("Got text for verification of Learn Catalog Page");
        return getTextFromElement(catalogPageText);
    }

    public LearnTechnicalAndTechnologyPage clickTechnicalAndTechnologyCard(){
        clickElement(technicalAndTechnologyCard);
        log.info("Clicked technical and technology card on the medium navigation menu");
        return new LearnTechnicalAndTechnologyPage(driver);
    }

}
