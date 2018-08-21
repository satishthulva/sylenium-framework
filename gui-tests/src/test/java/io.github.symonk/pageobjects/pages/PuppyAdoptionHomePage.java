package io.github.symonk.pageobjects.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class PuppyAdoptionHomePage {

  private static final SelenideElement BROOK_VIEW_DETAILS_BUTTON =
      $x("//form[@action='/puppies/4']//input");
  private static final SelenideElement HANNA_VIEW_DETAILS_BUTTON =
      $x("//form[@action='/puppies/3']//input");
  private static final SelenideElement CUSTOMER_MESSAGE = $(By.id("notice"));

  @Step("Opening the puppy adoption home page")
  public PuppyAdoptionHomePage openPage() {
    return Selenide.open("http://puppies.herokuapp.com/", PuppyAdoptionHomePage.class);
  }

  @Step("Viewing puppy details for Brook")
  public PuppyInformationPage viewBrookDetails() {
    BROOK_VIEW_DETAILS_BUTTON.click();
    return new PuppyInformationPage();
  }

  @Step("Viewing puppy details for Hannah")
  public PuppyInformationPage viewHannahDetails() {
    HANNA_VIEW_DETAILS_BUTTON.click();
    return new PuppyInformationPage();
  }

  @Step("Checking successful adoption message is shown")
  public void messageIsDisplayed(final String expectedMessage) {
    CUSTOMER_MESSAGE.should(text(expectedMessage));
  }
}
