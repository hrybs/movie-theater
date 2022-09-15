package com.jpmc.theater.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Reservation
 * The class is thread safe
 */
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Reservation {
    private final Customer customer;
    private final Showing showing;
    private final int audienceCount;
}