package com.jpmc.theater;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * Represents customer
 * The class is thread safe
 */
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(of="id")
@ToString
public class Customer {
    private final String id;
    private final String name;
}