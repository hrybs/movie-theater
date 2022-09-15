package com.jpmc.theater.domain;

import com.jpmc.theater.config.SpecialCode;
import lombok.Getter;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.annotation.Nonnull;
import java.math.BigDecimal;


/**
 * Represents movie
 * The class is thread safe
 */
@Getter
@EqualsAndHashCode
@ToString
public class Movie {
    private final MovieInfo movieInfo;
    private final BigDecimal ticketPrice;
    private final SpecialCode specialCode;

    /**
     * Create instance
     *
     * @param movieInfo movie info
     * @param ticketPrice ticket priice
     */
    public Movie(@Nonnull MovieInfo movieInfo, @Nonnull BigDecimal ticketPrice) {
        this(movieInfo, ticketPrice, null);
    }

    /**
     *
     * Create instance
     * @param movieInfo movie info
     * @param ticketPrice ticket price
     * @param specialCode special code
     */
    public Movie(@Nonnull MovieInfo movieInfo, @Nonnull BigDecimal ticketPrice, SpecialCode specialCode) {
        this.movieInfo = movieInfo;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

}