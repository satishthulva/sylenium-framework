package io.github.symonk.integrations;

import io.github.symonk.integrations.testrail.entity.TestRailTestRun;

import java.util.Map;

public interface TestRailIntegratable {

  int initiateRun(final TestRailTestRun run);

  TestRailIntegratable updateTestResults(final int runId, final Map<Integer, Integer> results);
}
