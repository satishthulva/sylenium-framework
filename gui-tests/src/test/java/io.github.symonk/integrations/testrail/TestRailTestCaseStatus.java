package io.github.symonk.integrations.testrail;

public enum TestRailTestCaseStatus {
  PASSED(1),
  BLOCKED(2),
  RETEST(4),
  FAILED(5);

  private int statusId;

  TestRailTestCaseStatus(final int statusId) {
    this.statusId = statusId;
  }

  public int getStatusId() {
    return this.statusId;
  }
}
