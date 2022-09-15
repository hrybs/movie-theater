package com.jpmc.theater.price.rule;

import com.jpmc.theater.domain.Showing;
import com.jpmc.theater.config.SpecialCode;
import com.jpmc.theater.price.domain.Discount;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Optional;

/**
 * Calculate discount for special movies
 * The class is thread safe
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Slf4j
public class SpecialMovieDiscountRule extends AbstractBastDiscountRule {

    private final Map<SpecialCode, Discount> codeToDiscountMap;

    /**
     * Create instance
     * @param codeToDiscountMap code to discount map
     */
    public SpecialMovieDiscountRule(Map<SpecialCode, Discount> codeToDiscountMap) {
        this.codeToDiscountMap = Map.copyOf(codeToDiscountMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    protected Optional<Discount> getDiscount(@Nonnull Showing showing) {
        log.debug("Trying to find discount for showing: {}, discount map: {}", showing, codeToDiscountMap);
        return Optional.ofNullable(codeToDiscountMap.get(showing.getMovie().getSpecialCode()));
    }

}
