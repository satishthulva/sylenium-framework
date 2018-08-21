package io.github.symonk.pageobjects.pages;

import com.codeborne.selenide.SelenideElement;
import io.github.symonk.domain.PuppyOrder;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class PuppyOrderDetailsPage {

  private static final SelenideElement PLACE_ORDER_BUTTON = $x("//*[@id='new_order']/div[6]/input");
  private static final SelenideElement ORDER_NAME_TEXTBOX = $(By.id("order_name"));
  private static final SelenideElement ORDER_ADDRESS_TEXTBOX = $(By.id("order_address"));
  private static final SelenideElement ORDER_EMAIL_TEXTBOX = $(By.id("order_email"));
  private static final SelenideElement ORDER_PAYMENT_TYPE = $(By.id("order_pay_type"));

  @Step("Completing the order details")
  public PuppyAdoptionHomePage fillInOrderDetails(final PuppyOrder order) {
    ORDER_NAME_TEXTBOX.val(order.getAdopterName());
    ORDER_ADDRESS_TEXTBOX.val(order.getAdopterAddress());
    ORDER_EMAIL_TEXTBOX.val(order.getAdopterEmail());
    ORDER_PAYMENT_TYPE.selectOption("Check");
    PLACE_ORDER_BUTTON.click();
    return new PuppyAdoptionHomePage();
  }
}
