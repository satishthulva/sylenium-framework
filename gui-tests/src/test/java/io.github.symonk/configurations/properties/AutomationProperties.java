package io.github.symonk.configurations.properties;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Properties;

@Slf4j
public class AutomationProperties implements ManagesFrameworkProperties {

    private final Properties properties = new Properties();

    public AutomationProperties() {
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("framework.properties"));
        } catch (IOException ex) {
            log.error("unable to find the framework properties file");
        }
    }

    @Override
    public String getBaseUrl() {
        return retrieveProperty("base.url");
    }

    @Override
    public String getBrowser() {
        return retrieveProperty("browser");
    }

    @Override
    public boolean shouldRunDistributed() {
        return Boolean.parseBoolean(retrieveProperty("use.selenium.grid"));
    }

    @Override
    public String getGridEndpoint() {
        return retrieveProperty("selenium.grid.endpoint");
    }

    @Override
    public int getWaitTimeout() {
        return Integer.parseInt(retrieveProperty("explicit.wait.timeout"));
    }

    @Override
    public String getBrowserDimensions() {
        return retrieveProperty("browser.dimensions");
    }

    @Override
    public boolean browserUseCustomDimensions() {
        return Boolean.parseBoolean(retrieveProperty("browser.use.custom.dimensions"));
    }

    private String retrieveProperty(final String key) {
        return properties.getProperty(key);
    }
}
