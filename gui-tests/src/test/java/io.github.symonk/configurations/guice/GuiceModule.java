package io.github.symonk.configurations.guice;

import org.aeonbits.owner.ConfigFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import io.github.symonk.common.helpers.localisation.LanguageHelper;
import io.github.symonk.common.helpers.localisation.ProvidesLanguageValues;
import io.github.symonk.configurations.properties.FrameworkProperties;
import io.github.symonk.data.OrderProvidable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GuiceModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(ProvidesLanguageValues.class).to(LanguageHelper.class).in(Singleton.class);
	install(new FactoryModuleBuilder()
		     .build(OrderProvidable.class));
  }

  @Provides
  @Singleton
  public FrameworkProperties getProperties() {
    return ConfigFactory.create(FrameworkProperties.class);
  }
}
