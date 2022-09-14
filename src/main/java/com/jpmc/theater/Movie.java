package com.jpmc.theater;

import com.jpmc.theater.domain.MovieInfo;
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
    private final long specialCode;

    /**
     * Create instance
     *
     * @param movieInfo movie info
     * @param ticketPrice ticket priice
     */
    public Movie(@Nonnull MovieInfo movieInfo, @Nonnull BigDecimal ticketPrice) {
        this(movieInfo, ticketPrice, 0);
    }

    /**
     *
     * Create instance
     * @param movieInfo movie info
     * @param ticketPrice ticket price
     * @param specialCode special code
     */
    public Movie(@Nonnull MovieInfo movieInfo, @Nonnull BigDecimal ticketPrice, long specialCode) {
        this.movieInfo = movieInfo;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

}