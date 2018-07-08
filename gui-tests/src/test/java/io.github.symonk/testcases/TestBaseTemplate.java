package io.github.symonk.testcases;

import com.codeborne.selenide.Selenide;
import io.github.symonk.common.helpers.localisation.LanguageHelper;
import io.github.symonk.common.helpers.localisation.ProvidesLanguageValues;
import io.github.symonk.configurations.properties.AutomationProperties;
import io.github.symonk.configurations.properties.ManagesFrameworkProperties;
import io.github.symonk.configurations.selenide.CustomListener;
import io.github.symonk.configurations.selenide.CustomSelenideLogger;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.logging.LogType;
import org.slf4j.MDC;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.logging.Level;

@Slf4j
public class TestBaseTemplate {

  private static final String TEST_NAME = "test";
  private static final CustomListener listener =
      new CustomListener().withPageSource(true).withScreenshot(true).withTestLog(true);

  private final ManagesFrameworkProperties properties = new AutomationProperties();
  final ProvidesLanguageValues languageHelper = new LanguageHelper(properties);

  @BeforeMethod(alwaysRun = true, description = "Initialize Test Logger")
  public void initiateLogger(final Method method) {
    startTestLogging(method.getName());
    log.info("Executing: + " + method.getName());
    CustomSelenideLogger.addListener("CustomListener", listener.setCurrentLog(method.getName()));
  }

  @AfterMethod(alwaysRun = true, description = "[Report] Parse Log File For Test")
  public void parseLogFileForTest(final Method method) {
    CustomSelenideLogger.setListenerLogFile(method.getName());
    stopTestLogging();
  }

  @AfterMethod(alwaysRun = true, description = "Clear Browser Session")
  public void preventBrowserSessionLeakage() {
    Selenide.clearBrowserLocalStorage();
    Selenide.clearBrowserCookies();
    Selenide.close();
  }

  @AfterMethod(alwaysRun = true, description = "Close Selenide Listener")
  public void unregisterTestListeners() {
    CustomSelenideLogger.removeAllListeners();
  }

  private void startTestLogging(String name) {
    log.info("Multi threaded logger initialized for test: " + name);
    MDC.put(TEST_NAME, name);
  }

  private String stopTestLogging() {
    String name = MDC.get(TEST_NAME);
    MDC.remove(TEST_NAME);
    return name;
  }
}
