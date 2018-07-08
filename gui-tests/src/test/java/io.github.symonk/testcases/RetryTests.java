package io.github.symonk.testcases;

import io.github.symonk.pageobjects.pages.GooglePage;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class RetryTests extends TestBaseTemplate {

  @Test(description = "Retried!", invocationCount = 1)
  @Story("This is a story")
  @Link(name = "allure", type = "mylink")
  @Issue("123")
  @TmsLink("456")
  @Severity(SeverityLevel.CRITICAL)
  public void retriedOnFailure() {
    open("/", GooglePage.class).searchFor("simon").someElement().should(visible);
  }
}
