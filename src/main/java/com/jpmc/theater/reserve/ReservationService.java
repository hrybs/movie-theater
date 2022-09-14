package com.jpmc.theater.reserve;

import com.jpmc.theater.Customer;
import com.jpmc.theater.Reservation;

import javax.annotation.Nonnull;

/**
 * Reservation Service
 */
public interface ReservationService {

    /**
     *
     * @param customer current customer
     * @param sequence current sequence
     * @param numberOfTickets number of tickets
     * @return reservation
     */
    @Nonnull
    Reservation reserve(@Nonnull Customer customer, int sequence, int numberOfTickets);

}
