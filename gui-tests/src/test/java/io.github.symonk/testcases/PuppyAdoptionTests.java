package io.github.symonk.testcases;

import io.github.symonk.common.helpers.localisation.ProvidesLanguageValues;
import io.github.symonk.configurations.guice.PropertiesModule;
import io.github.symonk.configurations.properties.ManagesFrameworkProperties;
import io.github.symonk.data.OrderProvider;
import io.github.symonk.domain.PuppyOrder;
import io.github.symonk.listeners.TestExecutionListener;
import io.github.symonk.pageobjects.pages.PuppyAdoptionHomePage;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Epic("Puppy Adoption Epic")
@Feature("Puppy Adoption Process Feature")
@Guice(modules = PropertiesModule.class)
@Listeners(TestExecutionListener.class)
public class PuppyAdoptionTests extends TestBaseTemplate {

  private final OrderProvider orderProvider;

  @Inject
  public PuppyAdoptionTests(
      final ManagesFrameworkProperties properties,
      final ProvidesLanguageValues languageHelper,
      final OrderProvider orderProvider) {
    super(properties, languageHelper);
    this.orderProvider = orderProvider;
  }

  @Test(description = "Hannah can be adopted")
  @Story("As a customer, I can adopt Hannah without any options")
  @Issue("ISS-001")
  @TmsLink("TMS-001")
  @Severity(SeverityLevel.CRITICAL)
  public void adoptingHannahWithoutAnyOptions() {
        new PuppyAdoptionHomePage()
            .openPage()
            .viewHannahDetails()
            .adoptPuppy()
            .completeTheAdoption()
            .fillInOrderDetails(orderProvider.getRandomOrder())
            .messageIsDisplayed(languageHelper.getResource("successful.adoption.message"));
  }

  @Test(description = "Brook can be adopted")
  @Story("As a customer, I can adopt Brook including all options")
  @Issue("ISS-002")
  @TmsLink("TMS-002")
  @Severity(SeverityLevel.CRITICAL)
  public void adoptingBrookWithAllOptions() {
    final PuppyOrder order = orderProvider.getRandomOrderWithAllOptions();
    new PuppyAdoptionHomePage()
            .openPage()
            .viewBrookDetails()
            .adoptPuppy()
            .completeTheAdoption(order)
            .fillInOrderDetails(order)
            .messageIsDisplayed(languageHelper.getResource("successful.adoption.message"));
  }

  @Test(description = "Cart reflects correct pricing for all options")
  @Story("As a customer, I am billed correctly for my options")
  @Issue("ISS-003")
  @TmsLink("TMS-003")
  @Severity(SeverityLevel.CRITICAL)
  public void optionsAreCorrectlyBilled() {
    final PuppyOrder order = orderProvider.getRandomOrderWithAllOptions();
    new PuppyAdoptionHomePage()
            .openPage()
            .viewBrookDetails()
            .adoptPuppy()
            .orderPriceForAllItemsIsCorrect(languageHelper.getResource("total.price.all.options"), order);
  }

  @Test(description = "This fails on purpose and gets retried")
  @Story("As an automation engineer, I want to retry a failed test")
  @Issue("ISS-004")
  @TmsLink("TMS-004")
  @Severity(SeverityLevel.CRITICAL)
  public void failedTestRetries() {
    new PuppyAdoptionHomePage()
            .openPage()
            .viewBrookDetails()
            .adoptPuppy();
    assertThat(true).isEqualTo(false);
  }



  @AfterClass(alwaysRun = true, description = "[Test Teardown]")
  public void afterClass() {
    log.info("This runs after each class");
  }
}
