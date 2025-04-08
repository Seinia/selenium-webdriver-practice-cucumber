package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import util.DriverFactory;
import util.ScreenshotUtil;

@Slf4j
public class BaseHooks {

    private WebDriver driver;

    @Before
    public void setUp(Scenario scenario) {
        DriverFactory.closeDriver();
        driver = DriverFactory.getDriver();
        log.info("Starting WebDriver for scenario: {}", scenario.getName());
    }
    @After
    public void stopBrowser(Scenario scenario) {
        if (scenario.isFailed()) {
            WebDriver driver = DriverFactory.getDriver();
            ScreenshotUtil.takeScreenshotForAllure(driver);
        }
        log.info("Closing WebDriver");
        DriverFactory.closeDriver();
    }
}
