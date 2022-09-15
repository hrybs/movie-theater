package com.jpmc.theater.price.rule;

import com.jpmc.theater.domain.Showing;

import javax.annotation.Nonnull;
import java.math.BigDecimal;

/**
 * Represents discount rule
 */
public interface DiscountRule {

    /**
     * Get price with discount
     * @return discount
     */
    @Nonnull
    BigDecimal getPriceWithDiscount(@Nonnull Showing showing);
}
