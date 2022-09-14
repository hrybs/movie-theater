package com.jpmc.theater.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.annotation.Nonnull;
import java.time.LocalTime;

/**
 * Represents time range
 * The class is thread safe
 */
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class TimeRange {

    private final LocalTime startTime;
    private final LocalTime endTime;

    /**
     * Show if given time is in range represented by this object
     * @param time given time
     * @return true if time is inside range. End time is exclusive.
     */
    public boolean isInRange(@Nonnull LocalTime time) {
        return !time.isBefore(startTime) && time.isBefore(endTime);
    }

}
