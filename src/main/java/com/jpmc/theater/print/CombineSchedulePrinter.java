package com.jpmc.theater.print;


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
    public CombineSchedulePrinter(List<SchedulePrinter> printers) {
        this.printers = List.copyOf(printers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void print() {
        printers.forEach(SchedulePrinter::print);
    }

}
