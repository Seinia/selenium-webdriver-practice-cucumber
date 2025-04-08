package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import model.CampusCourseTestData;
import model.LearnCourseTestData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.learn_portal.LearnCatalogPage;
import pages.learn_portal.LearnDotNetCoursePage;
import pages.learn_portal.LearnHomePage;
import pages.learn_portal.LearnTechnicalAndTechnologyPage;
import service.TestDataCampusCourseService;
import service.TestDataLearnCourseService;
import service.TestDataService;
import service.TestDataServiceDecorator;
import util.DriverFactory;


@Slf4j
public class CourseRegistrationOnLearnSteps {

    private LearnHomePage learnHomePage;
    private LearnCatalogPage learncatalogPage;
    private LearnTechnicalAndTechnologyPage learnTechnicalAndTechnologyPage;
    private LearnDotNetCoursePage learnDotNetCoursePage;
    private LearnCourseTestData testData;

    private final WebDriver driver = DriverFactory.getDriver();

    @Given("The user is on learn portal")
    public void theUserIsOnLearnPortal() {
        learnHomePage = new LearnHomePage(driver).openPage().acceptCookie();
        log.info("Browser is opened on learn portal and cookies are accepted");
        TestDataService<LearnCourseTestData> campusDataService =
                new TestDataServiceDecorator<>(new TestDataLearnCourseService());
        testData = campusDataService.getTestDataFromProperties();
        log.info("Test started with environment {} and with driver {}",
                System.getProperty("environment"),
                System.getProperty("browser"));
    }


    @When("The user navigates to catalogue portal")
    public void theUserNavigatesToCataloguePortal() {
        learncatalogPage = learnHomePage.clickCatalogButton();
        log.info("Navigated to Catalogue portal");
    }

    @Then("The user should see intuitive interface how to find a course on learn portal")
    public void theUserShouldSeeIntuitiveInterfaceHowToFindACourseOnLearnPortal() {
        Assert.assertEquals(testData.getCatalogPageText(),
                testData.getCatalogPageText(),
                "Catalog page text mismatch");
    }

    @Given("The user is on catalogue portal")
    public void theUserIsOnCataloguePortal() {
        theUserNavigatesToCataloguePortal();
    }

    @When("The user navigates to technical and technology section")
    public void theUserNavigatesToTechnicalAndTechnologySection() {
        learnTechnicalAndTechnologyPage = learncatalogPage.clickTechnicalAndTechnologyCard();
        log.info("Navigated to Technical and Technology section");
    }

    @And("The user apply language, estimation efforts, target level and vendor filters")
    public void theUserApplyLanguageEstimationEffortsTargetLevelAndVendorFilters() {
        learnTechnicalAndTechnologyPage
                .clickEnglishCheckBox()
                .clickMoreThanTwentyHoursCheckBox()
                .clickIntermediateCheckBox()
                .clickEpamCheckBox();
        log.info("Language, estimated efforts, target level, vendor filters were applied");
    }

    @Then("The user should see filters count")
    public void theUserShouldSeeFiltersCount() {
        Assert.assertEquals(learnTechnicalAndTechnologyPage.getListOfFiltersSize(),
                4,
                "Filters count mismatch");
    }

    @Given("The user navigated to technical and technology section and applied filters")
    public void theUserNavigatedToTechnicalAndTechnologySectionAndAppliedFilters() {
        theUserIsOnCataloguePortal();
        theUserNavigatesToTechnicalAndTechnologySection();
        theUserApplyLanguageEstimationEffortsTargetLevelAndVendorFilters();
    }

    @When("The user choose course card after filtering")
    public void theUserChooseCourseCardAfterFiltering() {
        learnDotNetCoursePage = learnTechnicalAndTechnologyPage
                .clickDotNetCourseCard();
        log.info("Navigated to Course card portal");
    }

    @Then("The user should see information according to course details")
    public void theUserShouldSeeInformationAccordingToCourseDetails() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(learnDotNetCoursePage.getLanguageText(),
                testData.getLanguageText(),
                "Course language mismatch");

        softAssert.assertEquals(learnDotNetCoursePage.getEstimatedEffortsText(),
                testData.getEstimatedEffortsText(),
                "Course duration mismatch");

        softAssert.assertAll();
    }

}
