package io.github.symonk.integrations.testrail;

import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.Project;
import com.codepine.api.testrail.model.Run;
import com.codepine.api.testrail.model.Section;
import com.codepine.api.testrail.model.Suite;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class TestRailClient {

  private final TestRail testRail;
  private Optional<Project> latestProject;
  private Optional<Suite> latestSuite;
  private Optional<Section> latestSection;
  private Optional<Run> latestRun;

  public TestRailClient(
      final String server,
      final String username,
      final String password,
      final String applicationName) {
    testRail = TestRail.builder(server, username, password).applicationName(applicationName).build();
  }

  public TestRailClient createProject(final String projectName) {
    latestProject = Optional.ofNullable(testRail.projects().add(new Project().setName(projectName)).execute());
    return this;
  }

  public TestRailClient createSuite(final String suiteName) {
    latestSuite = Optional.ofNullable(testRail.suites().add(latestProject.get().getId(), new Suite().setName(suiteName)).execute());
    return this;
  }

  public TestRailClient createSection(final String sectionName) {
    latestSection = Optional.ofNullable(testRail.sections().add(latestProject.get().getId(), new Section().setSuiteId(latestSuite.get().getId()).setName(sectionName)).execute());
    return this;
  }

  public TestRailClient createRun(final String runName) {
      latestRun = Optional.ofNullable(testRail.runs().add(latestProject.get().getId(), new Run().setSuiteId(latestSuite.get().getId()).setName(runName)).execute());
      return this;
  }







}
