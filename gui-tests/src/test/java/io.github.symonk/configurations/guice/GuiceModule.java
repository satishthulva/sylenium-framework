package io.github.symonk.configurations.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.github.symonk.common.helpers.localisation.LanguageHelper;
import io.github.symonk.common.helpers.localisation.ProvidesLanguageValues;
import io.github.symonk.configurations.properties.FrameworkProperties;
import io.github.symonk.data.OrderProvidable;
import io.github.symonk.data.PuppyOrderFactory;
import io.github.symonk.integrations.TestRailIntegratable;
import io.github.symonk.integrations.testrail.TestRailClient;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;

@Slf4j
public class GuiceModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(ProvidesLanguageValues.class).to(LanguageHelper.class).in(Singleton.class);
    bind(OrderProvidable.class).to(PuppyOrderFactory.class).in(Singleton.class);
    bind(TestRailIntegratable.class).to(TestRailClient.class).in(Singleton.class);
  }

  @Provides
  @Singleton
  public FrameworkProperties getProperties() {
    return ConfigFactory.create(FrameworkProperties.class);
  }
}
