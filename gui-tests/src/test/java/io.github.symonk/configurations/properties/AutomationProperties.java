package io.github.symonk.configurations.properties;

import io.github.symonk.common.enumerations.SupportedLanguage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.*;

@Slf4j
public class AutomationProperties implements ManagesFrameworkProperties {

  private static final String FRAMEWORK_PROPERTIES_FILE = "framework.properties";
  private static final String NO_PROPERTIES_FILE_FOUND = "Framework properties file could not be found, does it exist?";
  private static final String BASE_URL = "base.url";
  private static final String BROWSER = "selenide.browser";
  private static final String USE_SELENIUM_GRID = "use.selenium.grid";
  private static final String SELENIUM_GRID_ENDPOINT = "selenium.grid.endpoint";
  private static final String WAIT_TIMEOUT = "explicit.wait.timeout";
  private static final String BROWSER_DIMENSIONS = "browser.dimensions";
  private static final String USE_CUSTOM_DIMENSIONS = "browser.use.custom.dimensions";
  private static final String RETRY_ON_FAULURE = "retry.failures";
  private static final String NUMBER_OF_RETRIES = "number.of.retries";
  private static final String USE_PROXY = "tunnel.through.proxy";
  private static final String LANGUAGE = "language";
  private static final String NOT_SPECIFIED = "not specified";

  private final Properties properties = new Properties();

  public AutomationProperties() {
    initializeProperties();
  }

  private void initializeProperties() {
    try {
      properties.load(getClass().getClassLoader().getResourceAsStream(FRAMEWORK_PROPERTIES_FILE));
    } catch (final IOException ex) {
      throw new IllegalArgumentException(NO_PROPERTIES_FILE_FOUND);
    }
  }

  @Override
  public String getBaseUrl() {
    return retrieveProperty(BASE_URL);
  }

  @Override
  public String getBrowser() {
    return retrieveProperty(BROWSER);
  }

  @Override
  public boolean shouldRunDistributed() {
    return Boolean.parseBoolean(retrieveProperty(USE_SELENIUM_GRID));
  }

  @Override
  public String getGridEndpoint() {
    return retrieveProperty(SELENIUM_GRID_ENDPOINT);
  }

  @Override
  public int getWaitTimeout() {
    return Integer.parseInt(retrieveProperty(WAIT_TIMEOUT));
  }

  @Override
  public String getBrowserDimensions() {
    return retrieveProperty(BROWSER_DIMENSIONS);
  }

  @Override
  public boolean browserUseCustomDimensions() {
    return Boolean.parseBoolean(retrieveProperty(USE_CUSTOM_DIMENSIONS));
  }

  @Override
  public boolean retryOnFailure() {
    return Boolean.parseBoolean(retrieveProperty(RETRY_ON_FAULURE));
  }

  @Override
  public int numberOfRetries() {
    return Integer.parseInt(retrieveProperty(NUMBER_OF_RETRIES));
  }

  @Override
  public boolean tunnelThroughProxy() {
    return Boolean.parseBoolean(retrieveProperty(USE_PROXY));
  }

  @Override
  public Map<String, String> getPropertiesAsMap() {
    final Map<String, String> tempMap = new HashMap<>();

    final Enumeration<Object> keyValues = properties.keys();
    while (keyValues.hasMoreElements()) {
      final String key = (String) keyValues.nextElement();
      final String value = properties.getProperty(key);
      tempMap.put(key, System.getProperty(key, value));
    }

    return tempMap;
  }

  @Override
  public SupportedLanguage getLanguage() {
    return SupportedLanguage.valueOf(retrieveProperty(LANGUAGE).toUpperCase());
  }

  private String retrieveProperty(final String key) {
    return Optional.ofNullable(properties.getProperty(key)).orElse(NOT_SPECIFIED);
  }
}
