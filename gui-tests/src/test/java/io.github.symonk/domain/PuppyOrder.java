package io.github.symonk.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.gson.GsonBuilder;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import io.github.symonk.common.enumerations.OrderOptions;
import io.github.symonk.common.enumerations.Puppy;
import io.qameta.allure.Allure;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class PuppyOrder {

  private final String adopterName;
  private final String adopterAddress;
  private final String adopterEmail;
  private final Puppy dog;
  private final List<OrderOptions> listOfOrderItems;

  @AssistedInject
  public PuppyOrder(@Assisted("dog") Puppy dog, @Assisted("name") String adopterName, @Assisted("email") String adopterEmail, 
		  @Assisted("address") String adopterAddress, @Assisted("options") OrderOptions ... orderOptions){
	    this.dog = dog;
	    this.adopterName = adopterName;
	    this.adopterAddress = adopterAddress;
	    this.adopterEmail = adopterEmail;
	    if(orderOptions != null && orderOptions.length > 0) {
	    	listOfOrderItems = Arrays.asList(orderOptions);
	    } else {
	    	listOfOrderItems = new ArrayList<>();
	    }

	    // TODO : Allure call happening in the builder class need to be made here ?
  }
  
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
