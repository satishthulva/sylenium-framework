package io.github.symonk.pageobjects.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class GooglePage {

  @Step("Searching for a string {0}")
  public GooglePage searchFor(final String term) {
    log.info("Searching for a term");
    $(By.name("q")).val(term).pressEnter();
    return this;
  }

  @Step("Reloading the page")
  public void reloadThePage() {
    Selenide.refresh();
  }

  @Step("Returning some random element")
  public SelenideElement someElement() {
    return $(By.name("q"));
  }
}
