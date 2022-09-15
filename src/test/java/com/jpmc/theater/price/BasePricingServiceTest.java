package com.jpmc.theater.price;

import com.jpmc.theater.test.util.TestDataUtil;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BasePricingServiceTest {

    private BasePricingService basePricingService;

    @Test
    void shouldCalculateFinalPriceWhenDiscountPresent() {
        BigDecimal expectedPriceWithDiscount = TestDataUtil.TEST_FULL_PRICE
                .subtract(TestDataUtil.TIME_RANGE_VALUE_DISCOUNT.getValue())
                .multiply(BigDecimal.valueOf(3));
        basePricingService = new BasePricingService(TestDataUtil.getDiscountRules());
        assertThat(basePricingService.calculateFinalPrice(TestDataUtil.getShowing(), 3))
                .isEqualTo(expectedPriceWithDiscount);
    }

    @Test
    void shouldGetFullPrice() {
        basePricingService = new BasePricingService(Set.of());
        assertThat(basePricingService.getFullPrice(TestDataUtil.getShowing()))
                .isEqualTo(TestDataUtil.TEST_FULL_PRICE);
    }

}
