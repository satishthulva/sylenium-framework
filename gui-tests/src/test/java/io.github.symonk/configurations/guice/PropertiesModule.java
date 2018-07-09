package io.github.symonk.configurations.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import io.github.symonk.common.helpers.localisation.LanguageHelper;
import io.github.symonk.common.helpers.localisation.ProvidesLanguageValues;
import io.github.symonk.configurations.properties.AutomationProperties;
import io.github.symonk.configurations.properties.ManagesFrameworkProperties;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Properties;

@Slf4j
public class PropertiesModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(ManagesFrameworkProperties.class).to(AutomationProperties.class).in(Singleton.class);
    bind(ProvidesLanguageValues.class).to(LanguageHelper.class).in(Singleton.class);
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
