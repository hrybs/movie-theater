package com.jpmc.theater.reserve;

import com.google.common.base.Preconditions;
import com.jpmc.theater.domain.Customer;
import com.jpmc.theater.domain.Reservation;
import com.jpmc.theater.domain.Showing;
import com.jpmc.theater.schedule.ScheduleService;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Base reservation service
 * The class is thread safe
 */
@EqualsAndHashCode
@ToString
@Slf4j
public class BaseReservationService implements ReservationService {

    private final Map<Integer, Showing> sequenceToShowing;

    public BaseReservationService(@Nonnull ScheduleService scheduleService) {
        sequenceToShowing =
                scheduleService.getSchedule()
                        .stream()
                        .collect(Collectors.toUnmodifiableMap(Showing::getSequenceOfTheDay, Function.identity()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public Reservation reserve(@Nonnull Customer customer, int sequence, int numberOfTickets) {
        Preconditions.checkArgument(numberOfTickets > 0, "Number of tickets can't be less then 1. NumberOfTickets: %s", numberOfTickets);
        Preconditions.checkArgument(sequence > 0, "Sequence can't be less then 1. Sequence: %s", sequence);
        Preconditions.checkArgument(sequence <= sequenceToShowing.size(), "Sequence can't be bigger then max allowed: %s. Sequence: %s", sequenceToShowing.size(), sequence);

        Showing showing = sequenceToShowing.get(sequence);
        Reservation reservation = new Reservation(customer, showing, numberOfTickets);
        log.info("Reservation has been created: {}", reservation);
        return reservation;
    }

}
