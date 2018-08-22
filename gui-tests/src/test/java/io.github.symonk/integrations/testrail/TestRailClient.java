package io.github.symonk.integrations.testrail;

import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.Run;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class TestRailClient {

  private final TestRail testRail;

  public TestRailClient(final String server, final String username, final String password) {
    testRail = TestRail.builder(server, username, password).build();
  }

  public TestRailClient instantiateRunWithTests(
      final List<Integer> listOfTestCases,
      final int projectId,
      final int suiteId,
      final String runName) {
    testRail
        .runs()
        .add(
            projectId,
            new Run()
                .setSuiteId(suiteId)
                .setName(runName)
                .setIncludeAll(false)
                .setCaseIds(listOfTestCases))
        .execute();
    return this;
  }

  public TestRailClient instantiateRunWithAllTests(
      final int projectId, final int suiteId, final String runName) {
    testRail.runs().add(projectId, new Run().setSuiteId(suiteId).setName(runName)).execute();
    return this;
  }
}
