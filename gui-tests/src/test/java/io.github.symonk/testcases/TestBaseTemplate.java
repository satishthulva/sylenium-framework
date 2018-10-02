package io.github.symonk.testcases;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.github.symonk.common.helpers.localisation.ProvidesLanguageValues;
import io.github.symonk.configurations.guice.GuiceModule;
import io.github.symonk.listeners.WebEventListener;
import io.github.symonk.selenide.custom_listeners.CustomListener;
import io.github.symonk.selenide.custom_listeners.CustomSelenideLogger;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;

import javax.inject.Inject;
import java.lang.reflect.Method;

@Slf4j
@Guice(modules = GuiceModule.class)
public class TestBaseTemplate {

  private static final String TEST_NAME = "test";
  private static final CustomListener listener = new CustomListener().withPageSource(true).withScreenshot(true).withTestLog(true);

  protected final ProvidesLanguageValues languageHelper;

  @Inject
  public TestBaseTemplate(final ProvidesLanguageValues languageHelper) {
    this.languageHelper = languageHelper;
  }

  @BeforeMethod(alwaysRun = true, description = "[Register Driver Event Listener]")
  public void registerDriverEventListener() {
    WebDriverRunner.addListener(new WebEventListener());
  }

  @BeforeMethod(alwaysRun = true, description = "[Initialize Test Logger]")
  public void initiateLogger(final Method method) {
    startTestLogging(method.getName());
    log.info("Executing: + " + method.getName());
  }

  @BeforeMethod(alwaysRun = true, description = "[Registering Custom Selenide Listener]")
  public void registerCustomSelenideListener(final Method method) {
    CustomSelenideLogger.addListener("CustomListener", listener.setCurrentLog(method.getName()));
  }

  @AfterMethod(alwaysRun = true, description = "[Parse Test Logs]")
  public void parseLogFileForTest(final Method method) {
    CustomSelenideLogger.setListenerLogFile(method.getName());
    stopTestLogging();
  }

  @AfterMethod(alwaysRun = true, description = "[Clear Browser Session Data]")
  public void preventBrowserSessionLeakage() {
    //    Selenide.clearBrowserLocalStorage(); --> closing browser so no need to clean up state
    //    Selenide.clearBrowserCookies(); --> closing browser so no need to clean up state
    Selenide.close();
  }

  @AfterMethod(alwaysRun = true, description = "[Unregister Custom Selenide Listener]")
  public void unregisterTestListeners() {
    CustomSelenideLogger.removeAllListeners();
  }

  private void startTestLogging(final String name) {
    log.info("Multi threaded logger initialized for test: " + name);
    MDC.put(TEST_NAME, name);
  }

  private String stopTestLogging() {
    final String name = MDC.get(TEST_NAME);
    MDC.remove(TEST_NAME);
    return name;
  }
}
