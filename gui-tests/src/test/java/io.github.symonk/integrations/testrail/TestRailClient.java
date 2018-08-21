package io.github.symonk.integrations.testrail;

import io.github.symonk.configurations.properties.FrameworkProperties;
import io.github.symonk.integrations.TestRailIntegratable;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;

@Slf4j
public class TestRailClient implements TestRailIntegratable {

  private final RequestSpecBuilder builder = new RequestSpecBuilder();
  private static final String ADD_RUN = "/index.php?/api/v2/add_run/";

  @Inject
  public TestRailClient(final FrameworkProperties properties) {
    final PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
    authScheme.setUserName(properties.testRailUsername());
    authScheme.setPassword(properties.testRailPassword());

    builder
        .setContentType(ContentType.JSON)
        .setAuth(authScheme)
        .setBaseUri(properties.testrailEndpoint());
  }

  @Override
  public void initiateRun(final String name) {
    final Response response = given().spec(builder.build()).when().body(null).post(ADD_RUN);
    System.out.println(response.statusCode());
  }

  @Override
  public void addTestsToExecution() {}

  @Override
  public void updateTestResults() {}
}
