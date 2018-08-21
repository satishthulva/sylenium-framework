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
public class TestRailTestRun implements Serializable {

  private final int suite_id, milestoneId, assignedto_id;
  private final String name, description;
  private final boolean include_all;
  private final List<Integer> case_ids = new ArrayList<>();

  public TestRailTestRun addCaseToList(final Integer caseToAdd) {
    case_ids.add(caseToAdd);
    return this;
  }
}
