package com.jpmc.theater.price.rule;

import com.jpmc.theater.domain.Showing;
import com.jpmc.theater.test.util.TestDataUtil;
import com.jpmc.theater.config.SpecialCode;
import com.jpmc.theater.price.domain.Discount;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static com.jpmc.theater.test.util.TestDataUtil.SPECIAL_VALUE_DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;

public class SpecialMovieDiscountRuleTest {

    private SpecialMovieDiscountRule specialDiscountRule;

    @Test
    void shouldGetDiscountWhenPresent() {
        Map<SpecialCode, Discount> discountMap = TestDataUtil.getSpecialDiscountMap();
        specialDiscountRule = new SpecialMovieDiscountRule(discountMap);
        Showing showing = TestDataUtil.getShowing();
        BigDecimal expectedPriceWithDiscount =
                TestDataUtil.TEST_FULL_PRICE.subtract(SPECIAL_VALUE_DISCOUNT.getValue());

        assertThat(specialDiscountRule.getPriceWithDiscount(showing)).isEqualTo(expectedPriceWithDiscount);
    }

    @Test
    void shouldNotGetDiscountWhenAbsent() {
        Map<SpecialCode, Discount> discountMap = Map.of();
        specialDiscountRule = new SpecialMovieDiscountRule(discountMap);
        Showing showing = TestDataUtil.getShowing();
        BigDecimal expectedPriceWithDiscount = TestDataUtil.TEST_FULL_PRICE;

        assertThat(specialDiscountRule.getPriceWithDiscount(showing)).isEqualTo(expectedPriceWithDiscount);
    }

}
