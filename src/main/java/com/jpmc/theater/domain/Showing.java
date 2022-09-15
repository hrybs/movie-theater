package com.jpmc.theater.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Class represent particular showing
 * The class is thread safe
 */
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Showing {
    private final Movie movie;
    private final int sequenceOfTheDay;
    private final LocalDateTime showStartTime;
}
