package com.jpmc.theater.price.rule;

import com.jpmc.theater.domain.Showing;
import com.jpmc.theater.price.domain.Discount;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * Base abstract class for discount rule
 */
@Slf4j
public abstract class AbstractBastDiscountRule implements DiscountRule {

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public BigDecimal getPriceWithDiscount(@Nonnull Showing showing) {
        BigDecimal fullTicketPrice = showing.getMovie().getTicketPrice();
        Optional<Discount> discount = getDiscount(showing);
        log.debug("Found price discount: {}, for showing: {}", discount, showing);
        return discount
                .map(disc -> disc.applyDiscountOn(fullTicketPrice))
                .orElse(fullTicketPrice);
    }

    /**
     * Get discount for the given rule.
     *
     * @param showing current showing
     * @return discount
     */
    protected abstract Optional<Discount> getDiscount(@Nonnull Showing showing);

}
