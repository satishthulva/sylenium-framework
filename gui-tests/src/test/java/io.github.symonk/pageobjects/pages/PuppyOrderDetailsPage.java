package io.github.symonk.pageobjects.pages;

import com.codeborne.selenide.SelenideElement;
import io.github.symonk.domain.PuppyOrder;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class PuppyOrderDetailsPage {

  private static final SelenideElement PLACE_ORDER_BUTTON = $(By.xpath("//*[@id='new_order']/div[6]/input"));
  private static final SelenideElement ORDER_NAME_TEXTBOX = $(By.id("order_name"));
  private static final SelenideElement ORDER_ADDRESS_TEXTBOX = $(By.id("order_address"));
  private static final SelenideElement ORDER_EMAIL_TEXTBOX = $(By.id("order_email"));
  private static final SelenideElement ORDER_PAYMENT_TYPE = $(By.id("order_pay_type"));
  private static final SelenideElement CUSTOMER_MESSAGE = $(By.id("notice"));

  public PuppyAdoptionHomePage fillInOrderDetails(final PuppyOrder order) {
    ORDER_NAME_TEXTBOX.val(order.getAdopterName());
    ORDER_ADDRESS_TEXTBOX.val(order.getAdopterAddress());
    ORDER_EMAIL_TEXTBOX.val(order.getAdopterEmail());
    ORDER_PAYMENT_TYPE.selectOption("Check");
    return new PuppyAdoptionHomePage();
  }

  public String customerMessage() {
    return CUSTOMER_MESSAGE.text();
  }
}
