package com.jpmc.theater.price.rule;

import com.jpmc.theater.domain.Showing;
import com.jpmc.theater.price.domain.Discount;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Optional;

/**
 * Calculate date related discount rule
 * The class is thread safe
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Slf4j
public class DateOfMonthDiscountRule extends AbstractBastDiscountRule {

    private final Map<Integer, Discount> discountMap;

    /**
     * Create instance
     * @param discountMap discount map
     */
    public DateOfMonthDiscountRule(Map<Integer, Discount> discountMap) {
        this.discountMap = Map.copyOf(discountMap);
    }

    /**
     * Get discount some day of month.
     */
    @Override
    @Nonnull
    protected Optional<Discount> getDiscount(@Nonnull Showing showing) {
        int startDate = showing.getShowStartTime().getDayOfMonth();
        log.debug("Trying to find discount for showing: {}, discount map: {}", showing, discountMap);
        return Optional.ofNullable(discountMap.get(startDate));
    }

}
