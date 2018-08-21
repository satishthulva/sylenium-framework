package io.github.symonk.integrations.testrail;

import io.github.symonk.configurations.properties.FrameworkProperties;
import io.github.symonk.integrations.TestRailIntegratable;
import io.github.symonk.integrations.testrail.entity.TestRun;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;

@Slf4j
public class TestRailClient implements TestRailIntegratable {

  private static final String ADD_RUN = "/index.php?/api/v2/add_run/";
  private final RequestSpecBuilder builder = new RequestSpecBuilder();
  private final RequestSpecification spec;

  @Inject
  public TestRailClient(final FrameworkProperties properties) {
    final PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
    authScheme.setUserName(properties.testRailUsername());
    authScheme.setPassword(properties.testRailPassword());

    builder
        .setContentType(ContentType.JSON)
        .setAuth(authScheme)
        .setBaseUri(properties.testrailEndpoint());

    spec = builder.build().log().all();

  }

  @Override
  public TestRailIntegratable initiateRun(final TestRun run) {
    given().body(run).spec(spec).when().post(ADD_RUN + run.getSuiteId());
    return this;
  }

  @Override
  public TestRailIntegratable addTestsToExecution() {
      return this;
  }

  @Override
  public TestRailIntegratable updateTestResults() {
      return this;
  }

}
