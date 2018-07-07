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
    }

    @Override
    public void onExecutionFinish() {
        log.info("test completed!");
    }

    private void establishReportEnvironmentData() {

    }

    private void configureSelenide() {
        Configuration.baseUrl = automationProperties.getBaseUrl();
        Configuration.browser = automationProperties.getBrowser();
        if(automationProperties.shouldRunDistributed()) Configuration.remote = automationProperties.getGridEndpoint();
    }

}
