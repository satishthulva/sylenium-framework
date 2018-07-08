package io.github.symonk.common.helpers.reporting;

import io.github.symonk.configurations.properties.ManagesFrameworkProperties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReportHelper implements ReportInteractable {

    private final ManagesFrameworkProperties properties;

    public ReportHelper(final ManagesFrameworkProperties properties) {
        this.properties = properties;
    }

    @Override
    public void pushDynamicTestRunPropertiesToReport() {

    }

    private void


}
