package com.jpmc.theater.price.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ValueDiscountTest {

    private ValueDiscount valueDiscount;

    @Test
    void shouldCorrectApplyDiscountOn() {
        BigDecimal percentage = BigDecimal.valueOf(2);
        valueDiscount = new ValueDiscount(percentage);
        BigDecimal fullTime = BigDecimal.valueOf(200);
        BigDecimal expectedPriceWithDiscount = fullTime.subtract(percentage);

        assertThat(valueDiscount.applyDiscountOn(fullTime)).isEqualTo(expectedPriceWithDiscount);
    }

}
