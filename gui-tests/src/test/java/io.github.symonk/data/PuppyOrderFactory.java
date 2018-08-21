package io.github.symonk.data;

import io.github.symonk.common.enumerations.OrderOptions;
import io.github.symonk.common.enumerations.Puppy;
import io.github.symonk.domain.PuppyOrder;

public class PuppyOrderFactory implements OrderProvidable {

  @Override
  public PuppyOrder createRandomOrder() {
    return new PuppyOrder.PuppyOrderBuilder("random", "random-add", "rand@om.com", Puppy.HANNA)
        .build();
  }

  @Override
  public PuppyOrder createRandomOrderWithAllOptions() {
    return new PuppyOrder.PuppyOrderBuilder("random", "random-add", "rand@om.com", Puppy.BROOK)
        .addOptions(
            OrderOptions.COLLAR,
            OrderOptions.CHEW_TOY,
            OrderOptions.TRAVEL_CARRIER,
            OrderOptions.FIRST_VET_VISIT)
        .build();
  }

  @Override
  public PuppyOrder createPuppyOrder(
      final String name,
      final String address,
      final String email,
      final Puppy puppy,
      final OrderOptions... options) {
    return new PuppyOrder.PuppyOrderBuilder(name, address, email, puppy)
        .addOptions(options)
        .build();
  }
}
