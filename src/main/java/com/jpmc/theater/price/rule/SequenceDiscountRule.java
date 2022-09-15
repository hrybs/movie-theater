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
 * Calculate sequence related rule
 * The class is thread safe
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Slf4j
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
        log.debug("Trying to find discount for showing: {}, discount map: {}", showing, sequenceToDiscountMap);
        return Optional.ofNullable(sequenceToDiscountMap.get(showing.getSequenceOfTheDay()));
    }

}
