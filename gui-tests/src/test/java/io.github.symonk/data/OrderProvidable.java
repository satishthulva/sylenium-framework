package io.github.symonk.data;

import io.github.symonk.common.enumerations.OrderOptions;
import io.github.symonk.common.enumerations.Puppy;
import io.github.symonk.domain.PuppyOrder;

public interface OrderProvidable {

  PuppyOrder createRandomOrder();

  PuppyOrder createRandomOrderWithAllOptions();

  PuppyOrder createPuppyOrder(
      final String name,
      final String address,
      final String email,
      final Puppy puppy,
      final OrderOptions... options);
}
