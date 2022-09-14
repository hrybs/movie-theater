package com.jpmc.theater.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.annotation.Nonnull;
import java.time.Duration;

/**
 * Movie info
 */
@Getter
@EqualsAndHashCode
@ToString
public class MovieInfo {
    private final String title;
    private final Duration runningTime;
    private final String description;

    /**
     * Create instance
     *
     * @param title title
     * @param runningTime running time
     */
    public MovieInfo(@Nonnull String title, @Nonnull Duration runningTime) {
        this(title, runningTime, "");
    }

    /**
     * Create instance
     *
     * @param title title
     * @param runningTime running time
     * @param description description
     */
    public MovieInfo(@Nonnull String title, @Nonnull Duration runningTime, String description) {
        this.title = title;
        this.runningTime = runningTime;
        this.description = description;
    }

}
