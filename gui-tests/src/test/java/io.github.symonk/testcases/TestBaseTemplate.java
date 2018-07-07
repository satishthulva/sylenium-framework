package io.github.symonk.testcases;

import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.MDC;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Slf4j
public class TestBaseTemplate {

    public static final String TEST_NAME = "test_name";

    @BeforeMethod(alwaysRun = true, description = "Setup logger for test")
    public void setupLoggingCapabilitiesOfTheTest(final Method method) {
        startTestLogging(method.getName());
        log.info("Started Logger for: " + method.getName());
    }

    @AfterMethod
    public void addTestArtifacts(final Method method) {
        stopTestLogging();
        try {
            getScreenshotAsAttachment();
            getPageSourceAsAttachment();
            getJsonLogAsAttachment(method.getName());
        } catch (IOException exception) {
            log.error("Unable to find the log file for test: " + method.getName());
        }
    }

    private void startTestLogging(String name) {
        MDC.put(TEST_NAME, name);
    }


    private String stopTestLogging() {
        String name = MDC.get(TEST_NAME);
        MDC.remove(TEST_NAME);
        return name;
    }

    @Attachment(value = "Test Log", type = "application/json")
    private byte[] getJsonLogAsAttachment(String testName) throws IOException {
        File[] files = listFilesMatching(new File("target/test_logs/"), testName + "-.*\\.json");
        log.info("Adding log file: " + files[0].getPath());
        return Files.readAllBytes(Paths.get(files[0].getAbsolutePath()));
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] getScreenshotAsAttachment() {
        log.info(("Adding screenshot: Failure screenshot"));
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page Source", type = "text/plain")
    private String getPageSourceAsAttachment() {
        log.info(("Adding pagesource: Failed page source"));
        return getWebDriver().getPageSource();
    }


    private File[] listFilesMatching(File root, String regex) {
        if (!root.isDirectory()) {
            throw new IllegalArgumentException(root + " is not a directory.");
        }
        final Pattern p = Pattern.compile(regex);
        return root.listFiles(file -> p.matcher(file.getName()).matches());
    }


}
