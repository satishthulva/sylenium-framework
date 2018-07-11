package io.github.symonk.pageobjects.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class PuppyAdoptionHomePage {

  private static final SelenideElement HANNA_VIEW_DETAILS_BUTTON = $(By.xpath("//form[@action=\"/puppies/4\"]//input"));


  public PuppyAdoptionHomePage openPage() {
    return Selenide.open("http://puppies.herokuapp.com/", PuppyAdoptionHomePage.class);
  }

  public PuppyInformationPage viewHannahsDetails() {
      HANNA_VIEW_DETAILS_BUTTON.click();
      return new PuppyInformationPage();
  }

}
