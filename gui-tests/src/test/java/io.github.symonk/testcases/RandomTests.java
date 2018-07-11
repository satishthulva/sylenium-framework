package io.github.symonk.testcases;

import io.github.symonk.common.helpers.localisation.ProvidesLanguageValues;
import io.github.symonk.configurations.guice.PropertiesModule;
import io.github.symonk.configurations.properties.ManagesFrameworkProperties;
import io.github.symonk.data.OrderProvider;
import io.github.symonk.listeners.TestExecutionListener;
import io.github.symonk.pageobjects.pages.PuppyAdoptionHomePage;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import javax.inject.Inject;

@Slf4j
@Epic("Puppy Adoption Epic")
@Feature("Puppy Adoption Process Feature")
@Guice(modules = PropertiesModule.class)
@Listeners(TestExecutionListener.class)
public class RandomTests extends TestBaseTemplate {

  private final OrderProvider orderProvider;

  @Inject
  public RandomTests(
      final ManagesFrameworkProperties properties,
      final ProvidesLanguageValues languageHelper,
      final OrderProvider orderProvider) {
    super(properties, languageHelper);
    this.orderProvider = orderProvider;
  }

  @Test(description = "Hannah can be adopted")
  @Story("As a customer, I can adopt Hannah")
  @Issue("ISS-001")
  @TmsLink("TMS-001")
  @Severity(SeverityLevel.CRITICAL)
  public void adoptingHannahTest() {
        new PuppyAdoptionHomePage()
            .openPage()
            .viewHannahsDetails()
            .adoptHannah()
            .completeTheAdoption()
            .fillInOrderDetails(orderProvider.getRandomOrder())
            .messageIsDisplayed(languageHelper.getResource("successful.adoption.message"));
  }

  @AfterClass(alwaysRun = true, description = "Test Teardown")
  public void afterClass() {}
}
