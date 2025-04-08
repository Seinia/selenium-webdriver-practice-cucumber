package util;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class ScreenshotUtil {

    public static byte[] takeScreenshotForAllure(WebDriver driver) {
        if (driver == null) {
            log.warn("Driver is null, cannot take a screenshot.");
            return new byte[0];
        }

        try {
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            saveLocally(screenshotBytes);
            Allure.addAttachment("Screenshot", "image/png", new ByteArrayInputStream(screenshotBytes), "png");
            return screenshotBytes;
        } catch (Exception e) {
            log.error("Failed to take screenshot", e);
            return new byte[0];
        }
    }

    private static void saveLocally(byte[] bytes) throws IOException {
        String directoryPath = "target/screenshots/";
        File directory = new File(directoryPath);
        if (!directory.exists() && !directory.mkdirs()) {
            log.error("Failed to create directory: {}", directoryPath);
            return;
        }

        String pathName = directoryPath + getCurrentTimeAsString() + ".png";
        FileUtils.writeByteArrayToFile(new File(pathName), bytes);
        log.info("Screenshot was saved locally at {}", pathName);
    }

    private static String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }
}
