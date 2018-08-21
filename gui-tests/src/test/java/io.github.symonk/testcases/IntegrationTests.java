package io.github.symonk.testcases;

import com.google.inject.Inject;
import io.github.symonk.configurations.guice.GuiceModule;
import io.github.symonk.integrations.TestRailIntegratable;
import io.github.symonk.integrations.testrail.entity.TestRun;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

@Guice(modules = GuiceModule.class)
public class IntegrationTests {

  private final TestRailIntegratable testRailIntegratable;

  @Inject
  public IntegrationTests(final TestRailIntegratable testRailIntegratable) {
    this.testRailIntegratable = testRailIntegratable;
  }

  @Test
  public void addrun() {
    testRailIntegratable.initiateRun(TestRun.builder().name("Automation!").suiteId(1).includeAllTests(false).build().addCaseToList(1));
  }
}
