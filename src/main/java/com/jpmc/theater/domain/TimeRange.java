package com.jpmc.theater.domain;

import com.google.common.base.Preconditions;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.annotation.Nonnull;
import java.time.LocalTime;

/**
 * Represents time range.
 * The class is thread safe
 */
@Getter
@EqualsAndHashCode
@ToString
public class TimeRange {

    private final LocalTime startTime;
    private final LocalTime endTime;

    /**
     * Create instance.
     * end time should be bigger then start time
     */
    public TimeRange(@Nonnull LocalTime startTime, @Nonnull LocalTime endTime) {
        Preconditions.checkArgument(startTime.isBefore(endTime), "End time '%s' is smaller then start time '%s'", startTime, endTime);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Show if given time is in range represented by this object
     * @param time given time
     * @return true if time is inside range. End time is exclusive.
     */
    public boolean isInRange(@Nonnull LocalTime time) {
        return !time.isBefore(startTime) && time.isBefore(endTime);
    }

}
