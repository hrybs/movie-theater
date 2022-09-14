package com.jpmc.theater.price;

import com.jpmc.theater.Showing;

import javax.annotation.Nonnull;
import java.math.BigDecimal;

/**
 * Pricing service
 */
public interface PricingService {

    /**
     * Calculate final price including discount
     *
     * @param showing current showing
     * @param audienceCount audience count
     * @return discount
     */
    @Nonnull
    BigDecimal calculateFinalPrice(@Nonnull Showing showing, int audienceCount);

    /**
     * Get full price without any discounts
     *
     * @param showing the showing
     * @return full Price
     */
    @Nonnull
    BigDecimal getFullPrice(@Nonnull Showing showing);

}
