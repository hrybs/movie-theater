package com.jpmc.theater.price.rule;

import com.jpmc.theater.Showing;
import com.jpmc.theater.price.domain.Discount;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

/**
 * Calculate date related discount rule
 * The class is thread safe
 */
@EqualsAndHashCode(callSuper = true)
@ToString
public class DateDiscountRule extends AbstractBastDiscountRule {

    private final Map<LocalDate, Discount> discountMap;

    /**
     * Create instance
     * @param discountMap discount map
     */
    public DateDiscountRule(Map<LocalDate, Discount> discountMap) {
        this.discountMap = Map.copyOf(discountMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    protected Optional<Discount> getDiscount(@Nonnull Showing showing) {
        LocalDate startDate = showing.getShowStartTime().toLocalDate();
        return Optional.ofNullable(discountMap.get(startDate));
    }

}
