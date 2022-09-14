package com.jpmc.theater.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Local date provider
 * The class is thread safe
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LocalDateProvider {

    private static volatile LocalDateProvider instance = null;

    /**
     * Get instance
     * @return singleton provider
     */
    public static LocalDateProvider getInstance() {
        if (instance != null) {
            return instance;
        }

        synchronized (LocalDateProvider.class) {
            if (instance == null) {
                instance = new LocalDateProvider();
            }
            return instance;
        }

    }

    /**
     * Get current date
     * @return current date
     */
    public LocalDate currentDate() {
            return LocalDate.now();
    }

}
