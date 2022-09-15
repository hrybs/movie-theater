package com.jpmc.theater.config;

import com.jpmc.theater.domain.TimeRange;
import com.jpmc.theater.price.domain.Discount;
import com.jpmc.theater.price.domain.PercentageDiscount;
import com.jpmc.theater.price.domain.ValueDiscount;
import com.jpmc.theater.price.rule.DiscountRule;
import com.jpmc.theater.price.rule.DateOfMonthDiscountRule;
import com.jpmc.theater.price.rule.TimeRangeDiscountRule;
import com.jpmc.theater.price.rule.SequenceDiscountRule;
import com.jpmc.theater.price.rule.SpecialMovieDiscountRule;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Map;
import java.util.Set;

/**
 * Config class with discounts rules
 */
@UtilityClass
public final class DiscountConfig {

    /**
     * Get Discount rules
     * @return discount rules
     */
    public static Set<DiscountRule> getDiscountRules() {
        return Set.of(
                new DateOfMonthDiscountRule(getDateDiscountMap()),
                new TimeRangeDiscountRule(getTimeRangeDiscountMap()),
                new SequenceDiscountRule(getSequenceDiscountMap()),
                new SpecialMovieDiscountRule(getSpecialDiscountMap())
        );
    }

    /**
     * Get 1$ discount on 7th day of each month
     * @return discount map
     */
    private static Map<Integer, Discount> getDateDiscountMap() {
        return Map.of(7, new ValueDiscount(BigDecimal.valueOf(1)));
    }

    /**
     * Get 25% discount between 11am - 4pm
     * @return discount map
     */
    private static Map<TimeRange, Discount> getTimeRangeDiscountMap() {
        return Map.of(
                new TimeRange(LocalTime.of(11, 0), LocalTime.of(16, 0)), new PercentageDiscount(BigDecimal.valueOf(0.25))
        );
    }

    /**
     * Get 3$ discount when 1st showing in a day
     * Get 2$ discount when 2st showing in a day
     * @return discount map
     */
    private static Map<Integer, Discount> getSequenceDiscountMap() {
        return Map.of(
                1, new ValueDiscount(BigDecimal.valueOf(3)),
                2, new ValueDiscount(BigDecimal.valueOf(2))
        );
    }

    /**
     * Get 20% discount between when special code
     * @return discount map
     */
    private static Map<SpecialCode, Discount> getSpecialDiscountMap() {
        return Map.of(
                SpecialCode.SUPER_SPECIAL, new PercentageDiscount(BigDecimal.valueOf(0.20))
        );
    }

}
