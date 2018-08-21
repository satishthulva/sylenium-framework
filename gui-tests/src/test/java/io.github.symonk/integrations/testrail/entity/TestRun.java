package io.github.symonk.integrations.testrail.entity;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Builder
@Data
public class TestRun implements Serializable {

  private final int suiteId, milestoneId, assignedToId;
  private final String name, description;
  private final boolean includeAllTests;
  private final List<Integer> listOfCases = new ArrayList<>();

  public TestRun addCaseToList(final Integer caseToAdd) {
    listOfCases.add(caseToAdd);
    return this;
  }
}
