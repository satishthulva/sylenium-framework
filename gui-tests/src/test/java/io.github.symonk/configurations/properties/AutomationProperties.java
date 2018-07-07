package io.github.symonk.configurations.properties;

import java.io.IOException;
import java.util.Properties;


public class AutomationProperties implements ManagesFrameworkProperties {

    private final Properties properties = new Properties();

    public AutomationProperties() {
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("framework.properties"));
        } catch (IOException ex) {
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
        return Boolean.valueOf(retrieveProperty("use.selenium.grid"));
    }

    @Override
    public String getGridEndpoint() {
        return retrieveProperty("selenium.grid.endpoint");
    }

    @Override
    public int getWaitTimeout() {
        return Integer.parseInt(retrieveProperty("explicit.wait.timeout"));
    }

    private String retrieveProperty(final String key) {
        return properties.getProperty(key);
    }


}
