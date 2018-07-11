package io.github.symonk.configurations.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import io.github.symonk.common.helpers.localisation.LanguageHelper;
import io.github.symonk.common.helpers.localisation.ProvidesLanguageValues;
import io.github.symonk.configurations.properties.AutomationProperties;
import io.github.symonk.configurations.properties.ManagesFrameworkProperties;
import io.github.symonk.data.OrderProvider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PropertiesModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(ManagesFrameworkProperties.class).to(AutomationProperties.class).in(Singleton.class);
    bind(ProvidesLanguageValues.class).to(LanguageHelper.class).in(Singleton.class);
    bind(OrderProvider.class).in(Singleton.class);
  }
}
