package com.jpmc.theater.print;

import com.jpmc.theater.domain.Showing;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Used to print schedule in several formats
 * The class is thread safe
 */
public class CombineSchedulePrinter implements SchedulePrinter {

    private final List<SchedulePrinter> printers;

    /**
     * Create instance
     *
     * @param printers schedule printers
     */
    public CombineSchedulePrinter(@Nonnull List<SchedulePrinter> printers) {
        this.printers = List.copyOf(printers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void print(@Nonnull List<Showing> schedule) {
        printers.forEach(printer -> printer.print(schedule));
    }

}
