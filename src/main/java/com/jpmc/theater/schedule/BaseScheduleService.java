package com.jpmc.theater.schedule;

import com.jpmc.theater.util.LocalDateProvider;
import com.jpmc.theater.domain.Movie;
import com.jpmc.theater.domain.Showing;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class BaseScheduleService implements ScheduleService {

    private final LocalDateProvider dateProvider;

    private volatile List<Showing> schedule;

    /**
     * Create instance of class
     * @param dateProvider date provider
     * @param timeToMovieMap time to movie map
     */
    public BaseScheduleService(@NonNull LocalDateProvider dateProvider,
                               @Nonnull SortedMap<LocalTime, Movie> timeToMovieMap) {
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
        log.debug("Schedule has been calculated: {}", schedule);
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
