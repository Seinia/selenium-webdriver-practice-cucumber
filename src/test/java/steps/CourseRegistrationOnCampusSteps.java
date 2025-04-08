package steps;

import hooks.BaseHooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import model.CampusCourseTestData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import pages.campus_portal.CampusCoursePage;
import pages.campus_portal.CampusHomePage;
import pages.campus_portal.CampusLoginPage;
import pages.campus_portal.CampusTrainingPage;
import pages.learn_portal.LearnHomePage;
import service.TestDataCampusCourseService;
import service.TestDataService;
import service.TestDataServiceDecorator;
import util.DriverFactory;
import util.TestListener;

@Slf4j
public class CourseRegistrationOnCampusSteps {

    private LearnHomePage learnHomePage;
    private CampusHomePage campusHomePage;
    private CampusTrainingPage campusTrainingPage;
    private CampusCoursePage campusCoursePage;
    private CampusLoginPage campusLoginPage;
    private CampusCourseTestData testData;

    private final WebDriver driver = DriverFactory.getDriver();

    @Given("The user is on learn portal and isn't logged in")
    public void theUserIsOnLearnPortalAndIsnTLoggedIn() {
        learnHomePage = new LearnHomePage(driver).openPage().acceptCookie();
        log.info("Browser is opened on learn portal and cookies are accepted");
        TestDataService<CampusCourseTestData> campusDataService =
                new TestDataServiceDecorator<>(new TestDataCampusCourseService());
        testData = campusDataService.getTestDataFromProperties();
        log.info("Test started with environment {} and with driver {}",
                System.getProperty("environment"),
                System.getProperty("browser"));
    }

    @When("The user navigates to campus portal")
    public void theUserNavigatesToCampusPortal() {
        campusHomePage = learnHomePage.clickCampusButton();
        learnHomePage.switchToNewTab();
        log.info("Navigated to Campus homepage");
    }

    @Then("The user should see intuitive interface how to find a course")
    public void theUserShouldSeeIntuitiveInterfaceHowToFindACourse() {
        Assert.assertEquals(campusHomePage.getHomePageText(),
                testData.getCampusHomePageText(),
                "Campus page text mismatch");
        log.info("Test testCampusHomePageIsDisplayed PASSED\n");
    }

    @When("The user navigates to training portal")
    public void theUserNavigatesToTrainingPortal() {
        campusTrainingPage = campusHomePage
                .acceptCookie()
                .clickFindAProgramButton();
    }

    @Then("The user should see intuitive interface how to filter a course list")
    public void theUserShouldSeeIntuitiveInterfaceHowToFilterACourseList() {
        Assert.assertEquals(campusTrainingPage.getTrainingPageText(),
                testData.getTrainingPageText(),
                "Campus training page text mismatch");
    }

    @When("The user is on training portal")
    public void theUserIsOnTrainingPortal() {
        theUserNavigatesToCampusPortal();
        theUserNavigatesToTrainingPortal();
    }


    @And("The user apply locations and skills filters")
    public void theUserApplyLocationsAndSkillsFilters() {
        campusTrainingPage
                .clickLocationsDropDown()
                .inputSearchField(testData.getCourseLocation())
                .clickDropDownCheckBox()
                .clickSkillsDropDown()
                .inputSearchField(testData.getCourseSkill())
                .clickDropDownCheckBox();
    }

    @Then("The user should see courses according applied filters")
    public void theUserShouldSeeCoursesAccordingAppliedFilters() {
        Assert.assertEquals(campusTrainingPage.getCourseCardText(),
                testData.getCourseName(),
                "Course card text mismatch");
    }

    @When("The user choose any course card after filtering")
    public void theUserChooseAnyCourseCardAfterFiltering() {
        campusCoursePage = campusTrainingPage
                .clickCourseCard();
    }

    @And("The user try to register")
    public void theUserTryToRegister() {
        campusLoginPage = campusCoursePage
                .clickRegisterButton();
    }

    @Then("The user should see login page")
    public void theUserShouldSeeLoginPage() {
        Assert.assertEquals(campusLoginPage.getLoginPageText(),
                testData.getLoginPageText(),
                "Login page text mismatch");
    }

    @Given("The user is on campus portal")
    public void theUserIsOnCampusPortal() {
        theUserNavigatesToCampusPortal();
    }

    @Given("The user is on training portal and locations and skills filters are applied")
    public void theUserIsOnTrainingPortalAndLocationsAndSkillsFiltersAreApplied() {
        theUserIsOnTrainingPortal();
        theUserApplyLocationsAndSkillsFilters();
    }

}
