package io.github.symonk.pageobjects.pages;

import com.codeborne.selenide.SelenideElement;
import io.github.symonk.common.enumerations.OrderOptions;
import io.github.symonk.domain.PuppyOrder;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class PuppyCartPage {

  private static final SelenideElement COMPLETE_ADOPTION_BUTTON =
      $x("//*[@id='content']/div[2]/form[1]/div/input");
  private static final SelenideElement COLLAR_CHECKBOX = $(By.id("collar"));
  private static final SelenideElement TOY_CHECKBOX = $(By.id("toy"));
  private static final SelenideElement CARRIER_CHECKBOX = $(By.id("carrier"));
  private static final SelenideElement VET_CHECKBOX = $(By.id("vet"));
  private static final SelenideElement TOTAL_PRICE = $x("//td[@class='total_cell']/h2");

  @Step("Continuing with options without providing any options")
  public PuppyOrderDetailsPage completeTheAdoption() {
    return completeAdoption();
  }

  @Step("Checking total price for all options is correct")
  public PuppyCartPage orderPriceForAllItemsIsCorrect(final String cost, final PuppyOrder order) {
    selectAppropriateOptions(order);
    TOTAL_PRICE.shouldHave(exactText(cost));
    return this;
  }

  @Step("Populating the adoption options")
  public PuppyOrderDetailsPage completeTheAdoption(final PuppyOrder puppyOrder) {
    selectAppropriateOptions(puppyOrder);
    return completeTheAdoption();
  }

  private void selectAppropriateOptions(final PuppyOrder puppyOrder) {
    for (OrderOptions option : puppyOrder.getListOfOrderItems()) {
      if (option == OrderOptions.COLLAR) COLLAR_CHECKBOX.setSelected(true);
      if (option == OrderOptions.CHEW_TOY) TOY_CHECKBOX.setSelected(true);
      if (option == OrderOptions.TRAVEL_CARRIER) CARRIER_CHECKBOX.setSelected(true);
      if (option == OrderOptions.FIRST_VET_VISIT) VET_CHECKBOX.setSelected(true);
    }
  }

  private PuppyOrderDetailsPage completeAdoption() {
    COMPLETE_ADOPTION_BUTTON.click();
    return new PuppyOrderDetailsPage();
  }
}
