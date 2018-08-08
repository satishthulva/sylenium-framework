package io.github.symonk.listeners;

import com.codeborne.selenide.Configuration;
import io.github.symonk.common.helpers.reporting.ReportHelper;
import io.github.symonk.common.helpers.reporting.ReportInteractable;
import io.github.symonk.configurations.properties.FrameworkProperties;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.IExecutionListener;

@Slf4j
public class TestExecutionListener implements IExecutionListener {

  private final FrameworkProperties properties = ConfigFactory.create(FrameworkProperties.class);
  private final ReportInteractable reportHelper = new ReportHelper(properties);

  @Override
  public void onExecutionStart() {
    log.info("test run starting!");
    configureTestRun();
    pushReportInformation();
  }

  @Override
  public void onExecutionFinish() {
    log.info("test run finished!");
  }

  private void configureTestRun() {
    log.info("configuring the test run!");
    Configuration.browser = properties.selenideBrowser();
    manageChromeSwitches();
    if (properties.useSeleniumGrid()) {
      Configuration.remote = properties.seleniumGridEndpoint();
    }
    if (properties.useCustomBrowserDimensions()) {
      Configuration.startMaximized = false;
      Configuration.browserSize = properties.browserDimensions();
    }

    Configuration.baseUrl = properties.baseUrl();
    Configuration.timeout = properties.explicitWaitTimeout();
  }

  private void pushReportInformation() {
    log.info("pushing report information");
    this.reportHelper.pushDynamicTestRunPropertiesToReport();
  }

  private void manageChromeSwitches() {
    if(Configuration.browser.equalsIgnoreCase("chrome")) {
      final DesiredCapabilities caps = new DesiredCapabilities();
      final ChromeOptions options = new ChromeOptions();
      options.setHeadless(Configuration.headless);
      options.addArguments("--no-sandbox");
      caps.merge(options);
      Configuration.browserCapabilities = caps;
    }
  }

}
