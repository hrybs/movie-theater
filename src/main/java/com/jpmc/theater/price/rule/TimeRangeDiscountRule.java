package com.jpmc.theater.price.rule;

import com.jpmc.theater.Showing;
import com.jpmc.theater.price.domain.Discount;
import com.jpmc.theater.domain.TimeRange;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.annotation.Nonnull;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

/**
 * Calculate time range discount rule
 * The class is thread safe
 */
@EqualsAndHashCode(callSuper = true)
@ToString
public class TimeRangeDiscountRule extends AbstractBastDiscountRule {

    private final Map<TimeRange, Discount> discountMap;

    /**
     * Create instance
     * @param discountMap discount map
     */
    public TimeRangeDiscountRule(Map<TimeRange, Discount> discountMap) {
        this.discountMap = Map.copyOf(discountMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    protected Optional<Discount> getDiscount(@Nonnull Showing showing) {
        LocalTime startTime = showing.getShowStartTime().toLocalTime();
        return discountMap.entrySet()
                .stream()
                .filter(entry -> entry.getKey().isInRange(startTime))
                .map(Map.Entry::getValue)
                .max(Comparator.comparing(Discount::getValue));
    }

}
