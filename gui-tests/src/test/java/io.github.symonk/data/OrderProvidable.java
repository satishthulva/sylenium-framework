package io.github.symonk.data;

import com.google.inject.assistedinject.Assisted;

import io.github.symonk.common.enumerations.OrderOptions;
import io.github.symonk.common.enumerations.Puppy;
import io.github.symonk.domain.PuppyOrder;

public interface OrderProvidable {

  PuppyOrder createPuppyOrder(@Assisted("dog") Puppy dog, @Assisted("name") String adopterName, @Assisted("email") String adopterEmail, 
		  @Assisted("address") String adopterAddress, @Assisted("options") OrderOptions ... orderOptions);
}
