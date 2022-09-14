package com.jpmc.theater.price;

import com.jpmc.theater.Showing;
import com.jpmc.theater.price.rule.DiscountRule;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Set;

/**
 * Pricing service
 * The class is thread safe
 */
@EqualsAndHashCode
@ToString
public class BasePricingService implements PricingService {

    private final Set<DiscountRule> discountRules;

    /**
     * Create instance
     * @param discountRules discount rules
     */
    public BasePricingService(Set<DiscountRule> discountRules) {
        this.discountRules = Set.copyOf(discountRules);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public BigDecimal calculateFinalPrice(@Nonnull Showing showing, int audienceCount) {
        BigDecimal price = discountRules.stream()
                .map(rule -> rule.getPriceWithDiscount(showing))
                .min(Comparator.naturalOrder())
                .orElseGet(() -> showing.getMovie().getTicketPrice());
        return price.multiply(BigDecimal.valueOf(audienceCount));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public BigDecimal getFullPrice(@Nonnull Showing showing) {
        return showing.getMovie().getTicketPrice();
    }

}
