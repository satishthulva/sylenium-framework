package io.github.symonk.common.helpers.localisation;

import io.github.symonk.common.enumerations.SupportedLanguage;
import io.github.symonk.configurations.properties.ManagesFrameworkProperties;
import lombok.extern.slf4j.Slf4j;

import java.util.ResourceBundle;

@Slf4j
public class LanguageHelper implements ProvidesLanguageValues {

  private static final String DEFAULT_DIRECTORY = "localisation.";
  private final SupportedLanguage language;

  public LanguageHelper(final ManagesFrameworkProperties properties) {
    language = properties.getLanguage();
  }

  @Override
  public String getResource(final String key) {
    return ResourceBundle.getBundle(
            DEFAULT_DIRECTORY + language.getResourceFile(), language.getLocale())
        .getString(key);
  }
}