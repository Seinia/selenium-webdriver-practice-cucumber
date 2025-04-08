package util;

import io.cucumber.java.AfterStep;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver();
        ScreenshotUtil.takeScreenshotForAllure(driver);
    }

}