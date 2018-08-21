package io.github.symonk.pageobjects.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class PuppyInformationPage {

  private static final SelenideElement ADOPT_ME_BUTTON =
      $x("//*[@id='content']/div[2]/div/form/div/input[1]");

  @Step("Selecting adopt puppy")
  public PuppyCartPage adoptPuppy() {
    ADOPT_ME_BUTTON.click();
    return new PuppyCartPage();
  }
}
