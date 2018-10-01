package io.github.symonk.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.symonk.common.enumerations.OrderOptions;
import io.github.symonk.common.enumerations.Puppy;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Data
public class PuppyOrder {

  private final String adopterName;
  private final String adopterAddress;
  private final String adopterEmail;
  private final Puppy dog;
  private final List<OrderOptions> listOfOrderItems;

  private PuppyOrder(PuppyOrderBuilder builder) {
    this.dog = builder.dog;
    this.adopterName = builder.adopterName;
    this.adopterAddress = builder.adopterAddress;
    this.adopterEmail = builder.adopterEmail;
    this.listOfOrderItems = builder.listOfOrderItems;
  }

  @Override
  public String toString() {
    return new GsonBuilder().setPrettyPrinting().create().toJson(this);
  }

  public static class PuppyOrderBuilder {
    private final String adopterName;
    private final String adopterAddress;
    private final String adopterEmail;
    private final Puppy dog;
    private final List<OrderOptions> listOfOrderItems = new ArrayList<>();

    public PuppyOrderBuilder(
        final String adopterName,
        final String adopterAddress,
        final String adopterEmail,
        final Puppy dog) {
      this.adopterName = adopterName;
      this.adopterAddress = adopterAddress;
      this.adopterEmail = adopterEmail;
      this.dog = dog;
    }

    public PuppyOrderBuilder addOptions(OrderOptions... options) {
      Collections.addAll(listOfOrderItems, options);
      return this;
    }

    public PuppyOrder build() { ;
      Allure.addAttachment("Order.json", "application/json", new GsonBuilder().setPrettyPrinting().create().toJson(this));
      return new PuppyOrder(this);
    }
  }
}
