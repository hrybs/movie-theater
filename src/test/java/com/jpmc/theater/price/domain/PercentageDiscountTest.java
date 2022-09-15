package com.jpmc.theater.price.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class PercentageDiscountTest {

    private PercentageDiscount percentageDiscount;

    @Test
    void shouldCorrectApplyDiscountOn() {
        BigDecimal percentage = BigDecimal.valueOf(0.2);
        percentageDiscount = new PercentageDiscount(percentage);
        BigDecimal fullPrice = BigDecimal.valueOf(100);
        BigDecimal expectedPriceWithDiscount = fullPrice.multiply(percentage);

        assertThat(percentageDiscount.applyDiscountOn(fullPrice)).isEqualTo(expectedPriceWithDiscount);
    }

}
