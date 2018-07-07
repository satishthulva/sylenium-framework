package io.github.symonk.listeners;

import com.codeborne.selenide.Configuration;
import io.github.symonk.configurations.properties.AutomationProperties;
import lombok.extern.slf4j.Slf4j;
import org.testng.IExecutionListener;

@Slf4j
public class TestExecutionListener implements IExecutionListener {

    private final AutomationProperties automationProperties = new AutomationProperties();

    @Override
    public void onExecutionStart() {
        log.info("test started!");
        pushReportInformation();
        configureTestRun();
    }

    @Override
    public void onExecutionFinish() {
        log.info("test completed!");
    }

    private void pushReportInformation() {

    }

    private void configureTestRun() {
        Configuration.baseUrl = automationProperties.getBaseUrl();
        Configuration.browser = automationProperties.getBrowser();
        Configuration.timeout = automationProperties.getWaitTimeout();
        Configuration.browserSize = automationProperties.getBrowserDimensions();
        if (automationProperties.shouldRunDistributed()) Configuration.remote = automationProperties.getGridEndpoint();


    }

}
