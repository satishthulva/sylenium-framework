package io.github.symonk.configurations.dependency_injection;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Properties;

@Slf4j
public class FrameworkModule extends AbstractModule {

    @Override
    protected void configure() {
        prepareAutomationProperties();
    }

    private void prepareAutomationProperties() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("framework.properties"));
            Names.bindProperties(binder(), properties);
        } catch (IOException e) {
            log.error("unable to find/load the config for test automation properties");
        }
    }
}
