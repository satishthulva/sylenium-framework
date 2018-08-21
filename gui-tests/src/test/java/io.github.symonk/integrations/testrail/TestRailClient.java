package io.github.symonk.integrations.testrail;

import io.github.symonk.configurations.properties.FrameworkProperties;
import io.github.symonk.integrations.TestRailIntegratable;
import io.github.symonk.integrations.testrail.entity.TestRailResult;
import io.github.symonk.integrations.testrail.entity.TestRailTestRun;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;

import java.util.Map;

import static io.restassured.RestAssured.given;

@Slf4j
public class TestRailClient implements TestRailIntegratable {

  private static final String ADD_RUN = "/index.php?/api/v2/add_run/";
  private static final String SET_RESULT_FOR_TEST_CASE = "/index.php?/api/v2/add_result_for_case/";

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

    spec = builder.build().log().all().filter(new ResponseLoggingFilter());

  }

  @Override
  public int initiateRun(final TestRailTestRun run) {
    final Response response = given().spec(spec).body(run).when().post(ADD_RUN + run.getSuite_id());
    return response.then().extract().path("id");
  }

  @Override
  public TestRailIntegratable updateTestResults(final int runId, final Map<Integer, Integer> results) {
    results.forEach((test, result) -> {
      given().spec(spec).body(new TestRailResult(result, "Passed comment!")).when().post(SET_RESULT_FOR_TEST_CASE + "/" + runId + "/" + test);
    });
    return this;
  }


}
