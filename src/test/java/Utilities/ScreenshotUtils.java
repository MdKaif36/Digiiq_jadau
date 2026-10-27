package Utilities;

import aquality.selenium.browser.AqualityServices;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils {

    public static void takeScreenshot(String testName) {
        try {
            Path folder = Paths.get("screenshots");
            Files.createDirectories(folder);

            String timestamp = LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")
            );

            String fileName = testName + "_" + timestamp + ".png";
            Path filePath = folder.resolve(fileName);

            byte[] screenshot =
                    AqualityServices.getBrowser().getScreenshot();

            Files.write(filePath, screenshot);

            System.out.println(
                    "Screenshot saved at: " +
                            filePath.toAbsolutePath()
            );

        } catch (Exception e) {
            System.err.println(
                    "Failed to save screenshot: " + e.getMessage()
            );
        }
    }
}