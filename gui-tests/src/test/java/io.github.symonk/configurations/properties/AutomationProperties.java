package io.github.symonk.configurations.properties;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.InvalidArgumentException;

import java.io.IOException;
import java.util.*;

@Slf4j
public class AutomationProperties implements ManagesFrameworkProperties {

    private final static String FRAMEWORK_PROPERTIES_FILE = "framework.properties";
    private final static String NO_PROPERTIES_FILE_FOUND = "Framework properties file could not be found, does it exist?";
    private final static String BASE_URL = "base.url";
    private final static String BROWSER = "selenide.browser";
    private final static String USE_SELENIUM_GRID = "use.selenium.grid";
    private final static String SELENIUM_GRID_ENDPOINT = "selenium.grid.endpoint";
    private final static String WAIT_TIMEOUT = "explicit.wait.timeout";
    private final static String BROWSER_DIMENSIONS = "browser.dimensions";
    private final static String USE_CUSTOM_DIMENSIONS = "browser.use.custom.dimensions";
    private final static String RETRY_ON_FAULURE = "retry.failures";
    private final static String NUMBER_OF_RETRIES = "number.of.retries";
    private final static String USE_PROXY = "tunnel.through.proxy";
    private final static String NOT_SPECIFIED = "not specified";


    private final Properties properties = new Properties();

    public AutomationProperties() {
        initializeProperties();
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

    private String retrieveProperty(final String key) {
        return Optional.ofNullable(properties.getProperty(key)).orElse(NOT_SPECIFIED);
    }

    @Override
    public Map<String, String> getPropertiesAsMap() {
        Map<String, String> tempMap = new HashMap<>();

        Enumeration<Object> KeyValues = properties.keys();
        while (KeyValues.hasMoreElements()) {
            String key = (String) KeyValues.nextElement();
            String value = properties.getProperty(key);
            tempMap.put(key, System.getProperty(key, value));
        }

        return tempMap;
    }

    private void initializeProperties() {
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream(FRAMEWORK_PROPERTIES_FILE));
        } catch (IOException ex) {
            throw new IllegalArgumentException(NO_PROPERTIES_FILE_FOUND);
        }
    }


}
