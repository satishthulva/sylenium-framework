package io.github.symonk.selenide.custom_listeners;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.LogEventListener;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StatusDetails;
import io.qameta.allure.model.StepResult;
import io.qameta.allure.util.ResultsUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.regex.Pattern;

@Slf4j
public class CustomListener implements LogEventListener {

  private final AllureLifecycle lifecycle;
  private boolean withScreenshot;
  private boolean withPageSource;
  private boolean withTestLog;
  private String currentLog = "";

  public CustomListener() {
    this.lifecycle = Allure.getLifecycle();
  }

  private static byte[] getScreenshotBytes() {
    return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
  }

  private static byte[] getPageSourceBytes() {
    return WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
  }

  private static byte[] getLogFileBytes(final String testName) throws IOException {
    final File[] files = listFilesMatching(new File("target/test_logs/"), testName + "-.*\\.json");
    log.info("Adding log file: " + files[0].getPath());
    return Files.readAllBytes(Paths.get(files[0].getAbsolutePath()));
  }

  private static File[] listFilesMatching(final File root, final String regex) {
    if (!root.isDirectory()) {
      throw new IllegalArgumentException(root + " is not a directory.");
    }
    final Pattern p = Pattern.compile(regex);
    return root.listFiles(file -> p.matcher(file.getName()).matches());
  }

  public CustomListener withScreenshot(final boolean withScreenshot) {
    this.withScreenshot = withScreenshot;
    return this;
  }

  public CustomListener withPageSource(final boolean withPageSource) {
    this.withPageSource = withPageSource;
    return this;
  }

  public CustomListener withTestLog(final boolean withTestLog) {
    this.withTestLog = withTestLog;
    return this;
  }

  public CustomListener setCurrentLog(final String currentLog) {
    this.currentLog = currentLog;
    return this;
  }

  @Override
  public void onEvent(final LogEvent event) {
    lifecycle
        .getCurrentTestCase()
        .ifPresent(
            uuid -> {
              final String stepUUID = UUID.randomUUID().toString();
              lifecycle.startStep(
                  stepUUID, new StepResult().withName(event.toString()).withStatus(Status.PASSED));

              lifecycle.updateStep(
                  stepResult -> stepResult.setStart(stepResult.getStart() - event.getDuration()));

              if (LogEvent.EventStatus.FAIL.equals(event.getStatus())) {
                if (withScreenshot) {
                  lifecycle.addAttachment("Screenshot", "image/png", "png", getScreenshotBytes());
                }
                if (withPageSource) {
                  lifecycle.addAttachment("Page source", "text/html", "png", getPageSourceBytes());
                }
                try {
                  if (withTestLog) {
                    lifecycle.addAttachment(
                        "Test Log", "application/json", "json", getLogFileBytes(currentLog));
                  }
                } catch (final IOException exception) {
                  log.error(
                      "Could not find the log file for the current test, skipping the attachment");
                }

                lifecycle.updateStep(
                    stepResult -> {
                      final StatusDetails details =
                          ResultsUtils.getStatusDetails(event.getError())
                              .orElse(new StatusDetails());
                      stepResult.setStatus(
                          ResultsUtils.getStatus(event.getError()).orElse(Status.BROKEN));
                      stepResult.setStatusDetails(details);
                    });
              }
              lifecycle.stopStep(stepUUID);
            });
  }
}
