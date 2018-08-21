package io.github.symonk.common.helpers.localisation;

import io.github.symonk.common.enumerations.SupportedLanguage;
import io.github.symonk.configurations.properties.FrameworkProperties;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.ResourceBundle;

@Slf4j
public class LanguageHelper implements ProvidesLanguageValues {

  private static final String DEFAULT_DIRECTORY = "localisation.";
  private final SupportedLanguage language;

  @Inject
  public LanguageHelper(final FrameworkProperties properties) {
    language = SupportedLanguage.getLanguage(properties.language());
  }

  @Override
  public String getResource(final String key) {
    return ResourceBundle.getBundle(
            DEFAULT_DIRECTORY + language.getResourceFile(), language.getLocale())
        .getString(key);
  }
}
