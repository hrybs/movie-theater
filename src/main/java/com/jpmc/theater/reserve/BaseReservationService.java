package com.jpmc.theater.reserve;

import com.google.common.base.Preconditions;
import com.jpmc.theater.Customer;
import com.jpmc.theater.Reservation;
import com.jpmc.theater.Showing;
import com.jpmc.theater.schedule.ScheduleService;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
public class BaseReservationService implements ReservationService {

    private final Map<Integer, Showing> sequenceToShowing;

    public BaseReservationService(ScheduleService scheduleService) {
        sequenceToShowing =
                scheduleService.getSchedule().stream()
                        .collect(Collectors.toUnmodifiableMap(Showing::getSequenceOfTheDay, Function.identity()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public Reservation reserve(@Nonnull Customer customer, int sequence, int numberOfTickets) {
        Preconditions.checkArgument(numberOfTickets > 0, "Number of tickets can't be less then 1. NumberOfTickets: {}", numberOfTickets);
        Preconditions.checkArgument(sequence > 0, "Sequence can't be less then 1. Sequence: {}", sequence);
        Preconditions.checkArgument(sequenceToShowing.size() - 1 > 0, "Sequence can't be bigger then max allowed: {}. Sequence: {}", sequenceToShowing.size(), sequence);

        Showing showing = sequenceToShowing.get(sequence);
        return new Reservation(customer, showing, numberOfTickets);
    }

}
