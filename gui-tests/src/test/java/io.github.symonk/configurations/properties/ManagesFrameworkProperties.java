package io.github.symonk.configurations.properties;

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

}
