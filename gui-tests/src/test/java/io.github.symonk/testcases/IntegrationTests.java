package io.github.symonk.testcases;

import com.google.inject.Inject;
import io.github.symonk.configurations.guice.GuiceModule;
import io.github.symonk.integrations.TestRailIntegratable;
import io.github.symonk.integrations.testrail.entity.TestRailTestRun;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@Guice(modules = GuiceModule.class)
public class IntegrationTests {

  private final TestRailIntegratable testRailIntegratable;

  @Inject
  public IntegrationTests(final TestRailIntegratable testRailIntegratable) {
    this.testRailIntegratable = testRailIntegratable;
  }

  @Test
  public void addrun() {
    final int runId =
        testRailIntegratable.initiateRun(
            TestRailTestRun.builder()
                .name("Automation run!!")
                .description("Some Description!")
                .suite_id(1)
                .include_all(false)
                .assignedto_id(1)
                .build()
                .addCaseToList(1)
                .addCaseToList(2)
                .addCaseToList(3));
    Map<Integer, Integer> results = new HashMap<>();
    results.put(2, 1);
    results.put(1, 1);
    results.put(3, 1);
    testRailIntegratable.updateTestResults(runId, results);
  }
}
