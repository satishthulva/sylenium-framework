package io.github.symonk.configurations.properties;

import io.github.symonk.common.enumerations.SupportedLanguage;

import java.util.Map;

public interface ManagesFrameworkProperties {

  String getBaseUrl();

  String getBrowser();

  boolean shouldRunDistributed();

  String getGridEndpoint();

  int getWaitTimeout();

  String getBrowserDimensions();

  boolean browserUseCustomDimensions();

  boolean retryOnFailure();

  int numberOfRetries();

  boolean tunnelThroughProxy();

  Map<String, String> getPropertiesAsMap();

  SupportedLanguage getLanguage();
}
