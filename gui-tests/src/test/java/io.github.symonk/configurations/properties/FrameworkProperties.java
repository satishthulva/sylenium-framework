package io.github.symonk.configurations.properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.HotReload;
import org.aeonbits.owner.Config.Sources;

import java.util.Properties;

@HotReload
@Sources("classpath:framework.properties")
public interface FrameworkProperties extends Config {

  /** General Framework Properties */
  @Key("base.url")
  @DefaultValue("https://www.google.co.uk")
  String baseUrl();

  @Key("explicit.wait.timeout")
  @DefaultValue("15000")
  int explicitWaitTimeout(); // milliseconds

  @Key("language")
  @DefaultValue("english")
  String language();

  /** Browser Related Framework Properties */
  @Key("selenide.browser")
  @DefaultValue("chrome")
  String selenideBrowser();

  @Key("use.custom.dimensions")
  @DefaultValue("false")
  boolean useCustomBrowserDimensions();

  @Key("browser.dimensions")
  @DefaultValue("1920x1080")
  String browserDimensions();

  /** Test Distribution Framework Properties */
  @Key("use.selenium.grid")
  @DefaultValue("false")
  boolean useSeleniumGrid();

  @Key("selenium.grid.endpoint")
  @DefaultValue("http://localhost:4444/wd/hub")
  String seleniumGridEndpoint();

  /** Test Execution Configuration Properties */
  @Key("retry.failures")
  @DefaultValue("false")
  boolean retryOnFailure();

  @Key("number.of.retries")
  @DefaultValue("0")
  int numberOfFailRetries();

  @Key("tunnel.through.proxy")
  @DefaultValue("false")
  boolean useBrowserMobProxy();

  @Key("is.running.on.travis")
  @DefaultValue("false")
  boolean isThisRunningOnTravis();

  @Key("slack.enabled")
  @DefaultValue("false")
  boolean isSlackEnabled();

  @Key("slack.webhook.endpoint")
  String slackWebhookEndpoint();

  @Key("testrail.endpoint")
  String testrailEndpoint();

  @Key("testrail.username")
  String testRailUsername();

  @Key("testrail.password")
  String testRailPassword();

  @Key("testrail.enabled")
  @DefaultValue("false")
  boolean isTestRailEnabled();

  default Properties getAllProperties() {
    return new Properties() {
      {
        setProperty("base.url", baseUrl());
        setProperty("explicit.wait.timeout", String.valueOf(explicitWaitTimeout()));
        setProperty("language", language());
        setProperty("selenide.browser", selenideBrowser());
        setProperty("use.custom.dimensions?", String.valueOf(useCustomBrowserDimensions()));
        setProperty("custom.browser.dimensions", browserDimensions());
        setProperty("use.selenium.grid?", String.valueOf(useSeleniumGrid()));
        setProperty("selenium.grid.endpoint", seleniumGridEndpoint());
        setProperty("retry.failures?", String.valueOf(retryOnFailure()));
        setProperty("number.of.retries", String.valueOf(numberOfFailRetries()));
        setProperty("use.browsermob.proxy?", String.valueOf(useBrowserMobProxy()));
        setProperty("running.on.travis?", String.valueOf(isThisRunningOnTravis()));
        setProperty("slack.enabled", String.valueOf(isSlackEnabled()));
        setProperty("slack.webhook.endpoint", slackWebhookEndpoint());
        setProperty("testrail.endpoint", testrailEndpoint());
        setProperty("testrail.username", testRailUsername());
        setProperty("testrail.password", testRailPassword());
        setProperty("testrail.enabled", String.valueOf(isTestRailEnabled()));
      }
    };
  }
}
