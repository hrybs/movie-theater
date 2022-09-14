package com.jpmc.theater.price.rule;

import com.jpmc.theater.Showing;
import com.jpmc.theater.price.domain.Discount;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Optional;

/**
 * Calculate discount for special movies
 * The class is thread safe
 */
@EqualsAndHashCode(callSuper = true)
@ToString
public class SpecialMovieDiscountRule extends AbstractBastDiscountRule {

    private final Map<Long, Discount> codeToDiscountMap;

    /**
     * Create instance
     * @param codeToDiscountMap code to discount map
     */
    public SpecialMovieDiscountRule(Map<Long, Discount> codeToDiscountMap) {
        this.codeToDiscountMap = Map.copyOf(codeToDiscountMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    protected Optional<Discount> getDiscount(@Nonnull Showing showing) {
        return Optional.ofNullable(codeToDiscountMap.get(showing.getMovie().getSpecialCode()));
    }

}
