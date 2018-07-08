package io.github.symonk.testcases;

import io.github.symonk.configurations.dependency_injection.FrameworkModule;
import io.github.symonk.listeners.TestExecutionListener;
import io.github.symonk.pageobjects.pages.GooglePage;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

@Slf4j
@Epic("This is an Epic")
@Feature("This is a Feature")
@Guice(modules = FrameworkModule.class)
@Listeners(TestExecutionListener.class)
public class RandomTests extends TestBaseTemplate {

  @BeforeClass(description = "Test Configuration")
  public void beforeClass() {}

  @Test(description = "As a user I want to do something")
  @Story("This is a story")
  @Link(name = "allure", type = "mylink")
  @Issue("123")
  @TmsLink("456")
  @Severity(SeverityLevel.CRITICAL)
  public void testOne() {
    open("/", GooglePage.class).searchFor("simon").someElement().should(visible);
  }

  @Test(description = "Description Two")
  @Story("Another Story")
  @Link(name = "allure", type = "mylink")
  @Issue("999")
  @TmsLink("888")
  @Severity(SeverityLevel.CRITICAL)
  public void testTwo() {
    open("/", GooglePage.class).searchFor("not-duplicate").someElement().should(visible);
  }

  @Test(description = "This Test Is Disabled", enabled = false)
  @Link(name = "allure", type = "mylink")
  public void testThree() {
    open("/", GooglePage.class).searchFor("jenkins").someElement().should(visible);
  }

  @Test(description = "Localisation Test")
  public void testFour() {
    log.info(languageHelper.getResource("foo") + " is really foo?");
    log.info(languageHelper.getResource("bar") + " is really bar?");
  }

  @AfterClass(description = "Test Teardown")
  public void afterClass() {}
}
