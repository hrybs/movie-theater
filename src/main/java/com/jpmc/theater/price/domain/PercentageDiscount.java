package com.jpmc.theater.price.domain;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.annotation.Nonnull;
import java.math.BigDecimal;

/**
 * Discount that represents percentage value
 * The class is thread safe
 */
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class PercentageDiscount implements Discount {

    private final BigDecimal value;

    /**
     * Apply percentage amount on full price
     * @param fullPrice full price
     * @return price after discount
     */
    @Override
    @Nonnull
    public BigDecimal applyDiscountOn(@Nonnull BigDecimal fullPrice) {
        return fullPrice.multiply(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal getValue() {
        return value;
    }

}
