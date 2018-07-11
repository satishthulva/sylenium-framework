package io.github.symonk.data;

import io.github.symonk.common.enumerations.OrderOptions;
import io.github.symonk.common.enumerations.Puppy;
import io.github.symonk.domain.PuppyOrder;

public class OrderProvider {

  public PuppyOrder getOrder(
      final String name,
      final String address,
      final String email,
      final Puppy dog) {
    return new PuppyOrder.PuppyOrderBuilder(name, address, email, dog).build();
  }

  public PuppyOrder getOrderWithOptions(
      final String name,
      final String address,
      final String email,
      final Puppy dog,
      final OrderOptions... options) {
    return new PuppyOrder.PuppyOrderBuilder(name, address, email, dog).addOptions(options).build();
  }
}
