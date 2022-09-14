package com.jpmc.theater.price.rule;

import com.jpmc.theater.Showing;
import com.jpmc.theater.price.domain.Discount;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Optional;

/**
 * Calculate sequence related rule
 * The class is thread safe
 */
@EqualsAndHashCode(callSuper = true)
@ToString
public class SequenceDiscountRule extends AbstractBastDiscountRule {

    private final Map<Integer, Discount> sequenceToDiscountMap;

    /**
     * Create instance
     * @param sequenceToDiscountMap sequence to discount map
     */
    public SequenceDiscountRule(Map<Integer, Discount> sequenceToDiscountMap) {
        this.sequenceToDiscountMap = Map.copyOf(sequenceToDiscountMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    protected Optional<Discount> getDiscount(@Nonnull Showing showing) {
        return Optional.ofNullable(sequenceToDiscountMap.get(showing.getSequenceOfTheDay()));
    }

}
