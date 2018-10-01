package io.github.symonk.configurations.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;
import io.github.symonk.common.aop.AttachPostConstruct;
import io.github.symonk.common.aop.ModelConstructorInterceptor;
import io.github.symonk.common.aop.SomeThing;
import io.github.symonk.common.helpers.localisation.LanguageHelper;
import io.github.symonk.common.helpers.localisation.ProvidesLanguageValues;
import io.github.symonk.configurations.properties.FrameworkProperties;
import io.github.symonk.data.PuppyOrderProvidable;
import io.github.symonk.data.PuppyPuppyOrderFactory;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;

@Slf4j
public class GuiceModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(ProvidesLanguageValues.class).to(LanguageHelper.class).in(Singleton.class);
    bind(SomeThing.class).toInstance(new SomeThing());
    bind(PuppyOrderProvidable.class).to(PuppyPuppyOrderFactory.class).in(Singleton.class);
    bindInterceptor(Matchers.any(), Matchers.annotatedWith(AttachPostConstruct.class), new ModelConstructorInterceptor());
  }

  @Provides
  @Singleton
  public FrameworkProperties getProperties() {
    return ConfigFactory.create(FrameworkProperties.class);
  }
}
