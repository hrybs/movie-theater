package com.jpmc.theater.schedule;

import com.jpmc.theater.util.LocalDateProvider;
import com.jpmc.theater.Movie;
import com.jpmc.theater.Showing;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.SortedMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Base Implementation of {@link ScheduleService}
 * The class is thread safe
 */
@EqualsAndHashCode(of="schedule")
@ToString
public class BaseScheduleService implements ScheduleService {

    private final LocalDateProvider dateProvider;

    private volatile List<Showing> schedule;

    /**
     * Create instance of class
     * @param dateProvider date provider
     * @param timeToMovieMap time to movie map
     */
    public BaseScheduleService(LocalDateProvider dateProvider, SortedMap<LocalTime, Movie> timeToMovieMap) {
        this.dateProvider = dateProvider;
        refresh(timeToMovieMap);
    }

    /**
     * {@inheritDoc}
     */
    public void refresh(@Nonnull SortedMap<LocalTime, Movie> timeToMovieMap) {
        LocalDate currentDate = dateProvider.currentDate();
        AtomicInteger sequence = new AtomicInteger(1);
        this.schedule =  timeToMovieMap.entrySet()
                .stream()
                .map(entry -> new Showing(entry.getValue(), sequence.getAndIncrement(), LocalDateTime.of(currentDate, entry.getKey())))
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public List<Showing> getSchedule() {
        return schedule;
    }

}
