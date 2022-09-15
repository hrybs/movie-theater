package com.jpmc.theater.price.rule;

import com.jpmc.theater.domain.Showing;
import com.jpmc.theater.test.util.TestDataUtil;
import com.jpmc.theater.price.domain.Discount;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static com.jpmc.theater.test.util.TestDataUtil.DATE_VALUE_DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;

public class DateDiscountRuleTest {

    private DateOfMonthDiscountRule dateDiscountRule;

    @Test
    void shouldGetDiscountWhenPresent() {
        Map<Integer, Discount> discountMap = TestDataUtil.getDateDiscountMap();
        dateDiscountRule = new DateOfMonthDiscountRule(discountMap);
        Showing showing = TestDataUtil.getShowing();
        BigDecimal expectedPriceWithDiscount =
                TestDataUtil.TEST_FULL_PRICE.subtract(DATE_VALUE_DISCOUNT.getValue());

        assertThat(dateDiscountRule.getPriceWithDiscount(showing)).isEqualTo(expectedPriceWithDiscount);
    }

    @Test
    void shouldNotGetDiscountWhenAbsent() {
        Map<Integer, Discount> discountMap = Map.of();
        dateDiscountRule = new DateOfMonthDiscountRule(discountMap);
        Showing showing = TestDataUtil.getShowing();
        BigDecimal expectedPriceWithDiscount = TestDataUtil.TEST_FULL_PRICE;

        assertThat(dateDiscountRule.getPriceWithDiscount(showing)).isEqualTo(expectedPriceWithDiscount);
    }

}
