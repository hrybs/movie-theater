package com.jpmc.theater.price.domain;

import javax.annotation.Nonnull;
import java.math.BigDecimal;

/**
 * Represents discount
 */
public interface Discount {

    /**
     * Get result value after applying discount
     * @param fullPrice full price
     * @return price after discount
     */
    @Nonnull
    BigDecimal applyDiscountOn(@Nonnull BigDecimal fullPrice);

    /**
     * Get raw value
     * @return value
     */
    BigDecimal getValue();

}
