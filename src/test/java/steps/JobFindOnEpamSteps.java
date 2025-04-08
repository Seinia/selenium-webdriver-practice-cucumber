package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import model.EpamCareersTestData;
import model.LearnCourseTestData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.epam_portal.EpamCareersPage;
import pages.epam_portal.EpamHomePage;
import pages.epam_portal.EpamJobDetailsPage;
import pages.epam_portal.EpamJobListPage;
import pages.learn_portal.LearnHomePage;
import service.TestDataEpamCareersService;
import service.TestDataLearnCourseService;
import service.TestDataService;
import service.TestDataServiceDecorator;
import util.DriverFactory;

import java.util.List;
import java.util.Map;

@Slf4j
public class JobFindOnEpamSteps {

    private EpamHomePage epamHomePage;
    private EpamCareersPage epamCareersPage;
    private EpamJobListPage epamJobListPage;
    private EpamJobDetailsPage epamJobDetailsPage;
    private EpamCareersTestData testData;

    private WebDriver driver = DriverFactory.getDriver();

    @Given("The user is on epam portal")
    public void theUserIsOnEpamPortal() {
        epamHomePage = new EpamHomePage(driver).openPage();
        log.info("Browser is opened on epam portal and cookies are accepted");
        TestDataService<EpamCareersTestData> epamCareersDataService =
                new TestDataServiceDecorator<>(new TestDataEpamCareersService());
        testData = epamCareersDataService.getTestDataFromProperties();
        log.info("Test started with environment {} and with driver {}",
                System.getProperty("environment"),
                System.getProperty("browser"));
    }

    @When("The user navigates to epam careers section")
    public void theUserNavigatesToEpamCareersSection() {
        epamCareersPage = epamHomePage.clickCareersButton();
        log.info("Navigated to Careers section");
    }

    @Then("The user should see easy-to-read interface how to find a job")
    public void theUserShouldSeeEasyToReadInterfaceHowToFindAJob() {
        Assert.assertEquals(epamCareersPage.getEpamCareersPageText(),
                testData.getCareersPageText(),
                "Careers page text mismatch");
    }

    @Given("The user is on epam careers section of epam portal")
    public void theUserIsOnEpamCareersSectionOfEpamPortal() {
        theUserNavigatesToEpamCareersSection();
    }

    @When("The user navigates to epam careers portal")
    public void theUserNavigatesToEpamCareersPortal() {
        epamJobListPage = epamCareersPage
                .clickFindButton()
                .clickVisitButton();
        epamCareersPage.switchToNewTab();
        log.info("Navigated to Epam Careers portal");
    }

    @And("The user input {string}, specialisation and location")
    public void theUserInputSpecialisationAndLocation(String skill) {
        epamJobListPage
                .acceptCookie()
                .inputSkillsField(skill)
                .clickSpecialisationTextBox()
                .clickSpecialisationCheckBox()
                .inputLocationTextBox();
        log.info("Skills, specialisation and location data was inputed");
    }

    public void theUserInputSkillsSpecialisationAndLocation() {
        epamJobListPage
                .acceptCookie()
                .inputSkillsField(testData.getJobSkill())
                .clickSpecialisationTextBox()
                .clickSpecialisationCheckBox()
                .inputLocationTextBox();
        log.info("Skills, specialisation and location data was inputed");
    }

    @Then("The user should see job card according to applied {string}")
    public void theUserShouldSeeJobCardAccordingToApplied(String skill) {
        Assert.assertTrue(epamJobListPage.getJobCardText(skill)
                        .trim()
                        .contains(skill),
                "Job card title mismatch");
    }

    @Given("The user is on epam careers portal and applied filters")
    public void theUserIsOnEpamCareersPortalAndAppliedFilters() {
        theUserIsOnEpamCareersSectionOfEpamPortal();
        theUserNavigatesToEpamCareersPortal();
        theUserInputSkillsSpecialisationAndLocation();
    }


    @When("The user choose any job card with applied {string}")
    public void theUserChooseAnyJobCardWithApplied(String skill) {
        epamJobDetailsPage = epamJobListPage
                .clickJobCard(skill);
    }

    @Then("The user should see job details according to applied {string}")
    public void theUserShouldSeeJobDetailsAccordingToApplied(String skills) {
        Assert.assertTrue(epamJobDetailsPage.getJobTitleText()
                        .contains(skills),
                "Job title mismatch");
    }

    @Given("The user is on epam careers portal and applied {string}")
    public void theUserIsOnEpamCareersPortalAndApplied(String skills) {
        theUserIsOnEpamCareersSectionOfEpamPortal();
        theUserNavigatesToEpamCareersPortal();
        theUserInputSpecialisationAndLocation(skills);
    }

    @Given("The user is on epam careers portal")
    public void theUserIsOnEpamCareersPortal() {
        theUserIsOnEpamCareersSectionOfEpamPortal();
        theUserNavigatesToEpamCareersPortal();
    }


    @And("The user has applied the following filters:")
    public void theUserHasAppliedTheFollowingFilters(DataTable filtersTable) {
        epamJobListPage
                .clickSpecialisationTextBox()
                .clickSpecialisationCheckBox()
                .inputLocationTextBox();
        List<Map<String, String>> filters = filtersTable.asMaps(String.class, String.class);
        for (Map<String, String> filter : filters) {
            String skills = filter.get("skills");

            epamJobListPage
                    .inputSkillsField(skills);

            log.info("Applied filters: Skills={}", skills);
        }
        epamJobListPage.clickSearchButton();
    }

    @Then("The user should see job details according to applied filters")
    public void theUserShouldSeeJobDetailsAccordingToAppliedFilters() {
        Assert.assertTrue(epamJobListPage.getJobCardText("Node.js")
                        .trim()
                        .contains("Node.js"),
                "Job card title mismatch");
    }
}
