package io.github.symonk.integrations;

import io.github.symonk.integrations.testrail.entity.TestRun;

public interface TestRailIntegratable {

  TestRailIntegratable initiateRun(final TestRun run);

  TestRailIntegratable addTestsToExecution();

  TestRailIntegratable updateTestResults();
}
