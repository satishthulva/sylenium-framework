package io.github.symonk.integrations.testrail.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
@Data
public class TestRailResult implements Serializable {

  private final int status_id;
  private final String comment;

  public TestRailResult(final int status_id, final String comment) {
    this.status_id = status_id;
    this.comment = comment;
  }
}
