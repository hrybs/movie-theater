package com.jpmc.theater.print;

import com.jpmc.theater.domain.Showing;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Schedule printer
 */
public interface SchedulePrinter {

    /**
     * Print schedule
     *
     */
    void print(@Nonnull List<Showing> schedule);
}
