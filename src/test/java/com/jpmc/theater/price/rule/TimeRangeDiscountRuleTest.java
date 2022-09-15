package com.jpmc.theater.price.rule;

import com.jpmc.theater.domain.Showing;
import com.jpmc.theater.test.util.TestDataUtil;
import com.jpmc.theater.domain.TimeRange;
import com.jpmc.theater.price.domain.Discount;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static com.jpmc.theater.test.util.TestDataUtil.TIME_RANGE_VALUE_DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;

public class TimeRangeDiscountRuleTest {

    private TimeRangeDiscountRule timeRangeDiscountRule;

    @Test
    void shouldGetDiscountWhenPresent() {
        Map<TimeRange, Discount> discountMap = TestDataUtil.getTimeRangeDiscountMap();
        timeRangeDiscountRule = new TimeRangeDiscountRule(discountMap);
        Showing showing = TestDataUtil.getShowing();
        BigDecimal expectedPriceWithDiscount =
                TestDataUtil.TEST_FULL_PRICE.subtract(TIME_RANGE_VALUE_DISCOUNT.getValue());

        assertThat(timeRangeDiscountRule.getPriceWithDiscount(showing)).isEqualTo(expectedPriceWithDiscount);
    }

    @Test
    void shouldNotGetDiscountWhenAbsent() {
        Map<TimeRange, Discount> discountMap = Map.of();
        timeRangeDiscountRule = new TimeRangeDiscountRule(discountMap);
        Showing showing = TestDataUtil.getShowing();
        BigDecimal expectedFullPrice = showing.getMovie().getTicketPrice();

        assertThat(timeRangeDiscountRule.getPriceWithDiscount(showing)).isEqualTo(expectedFullPrice);
    }

    @Test
    void shouldThrowExceptionWhenCreateInstanceWithIncorrectTimes() {
        Map<TimeRange, Discount> discountMap = Map.of();
        timeRangeDiscountRule = new TimeRangeDiscountRule(discountMap);
        Showing showing = TestDataUtil.getShowing();
        BigDecimal expectedFullPrice = showing.getMovie().getTicketPrice();

        assertThat(timeRangeDiscountRule.getPriceWithDiscount(showing)).isEqualTo(expectedFullPrice);
    }

}
