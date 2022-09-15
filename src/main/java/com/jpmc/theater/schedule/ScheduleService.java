package com.jpmc.theater.schedule;

import com.jpmc.theater.domain.Movie;
import com.jpmc.theater.domain.Showing;

import javax.annotation.Nonnull;
import java.time.LocalTime;
import java.util.List;
import java.util.SortedMap;

/**
 * Schedule Service
 */
public interface ScheduleService {

    /**
     * Get Schedule
     * @return schedule
     */
    @Nonnull
    List<Showing> getSchedule();

    /**
     * Refresh this schedule with new value for current day
     * @param timeToMovieMap
     */
   void refresh(@Nonnull SortedMap<LocalTime, Movie> timeToMovieMap);

}
