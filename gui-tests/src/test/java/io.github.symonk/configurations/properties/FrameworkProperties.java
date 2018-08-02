package io.github.symonk.configurations.properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.HotReload;
import org.aeonbits.owner.Config.Sources;

@HotReload
@Sources("classpath:foo/bar/baz.properties")
public interface FrameworkProperties extends Config {

  /** General Framework Properties */
  @Key("base.url")
  @DefaultValue("https://www.google.co.uk")
  String baseUrl();

  @Key("explicit.wait.timeout")
  @DefaultValue("15000")
  int explicitWaitTimeout(); // milliseconds

  @Key("language")
  @DefaultValue("en")
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
}
