package io.github.symonk.testcases;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.github.symonk.common.helpers.localisation.LanguageHelper;
import io.github.symonk.common.helpers.localisation.ProvidesLanguageValues;
import io.github.symonk.configurations.properties.AutomationProperties;
import io.github.symonk.configurations.properties.ManagesFrameworkProperties;
import io.github.symonk.configurations.selenide.CustomListener;
import io.github.symonk.configurations.selenide.CustomSelenideLogger;
import lombok.extern.slf4j.Slf4j;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.slf4j.MDC;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

@Slf4j
public class TestBaseTemplate {

  private static final String TEST_NAME = "test";
  private static final CustomListener listener = new CustomListener().withPageSource(true).withScreenshot(true).withTestLog(true);
  private final BrowserMobProxy proxyServer = new BrowserMobProxyServer();

  private final ManagesFrameworkProperties properties = new AutomationProperties();
  final ProvidesLanguageValues languageHelper = new LanguageHelper(properties);

  @BeforeSuite(alwaysRun = true, description = "Configure proxy")
  public void setupProxy() {
    if (properties.tunnelThroughProxy()) {
      proxyServer.start();
      WebDriverRunner.setProxy(ClientUtil.createSeleniumProxy(proxyServer));
    }
  }

  @BeforeMethod(alwaysRun = true, description = "Initialize Test Logger")
  public void initiateLogger(final Method method) {
    startTestLogging(method.getName());
    log.info("Executing: + " + method.getName());
    CustomSelenideLogger.addListener("CustomListener", listener.setCurrentLog(method.getName()));
  }

  private void startTestLogging(String name) {
    log.info("Multi threaded logger initialized for test: " + name);
    MDC.put(TEST_NAME, name);
  }

  @AfterMethod(alwaysRun = true, description = "[Report] Parse Log File For Test")
  public void parseLogFileForTest(final Method method) {
    CustomSelenideLogger.setListenerLogFile(method.getName());
    stopTestLogging();
  }

  private String stopTestLogging() {
    String name = MDC.get(TEST_NAME);
    MDC.remove(TEST_NAME);
    return name;
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

  @AfterSuite(alwaysRun = true, description = "collect .har data")
  public void correlateHarData() {
    if (properties.tunnelThroughProxy()) {
      proxyServer.getHar();
      proxyServer.stop();
    }
  }
}
