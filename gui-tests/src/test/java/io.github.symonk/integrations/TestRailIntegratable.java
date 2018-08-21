package io.github.symonk.integrations;


public interface TestRailIntegratable {

    void initiateRun(final String name);
    void addTestsToExecution();
    void updateTestResults();

}
