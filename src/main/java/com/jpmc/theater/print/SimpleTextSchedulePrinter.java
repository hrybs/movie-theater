package com.jpmc.theater.print;

import com.jpmc.theater.price.PricingService;
import com.jpmc.theater.schedule.ScheduleService;
import com.jpmc.theater.util.LocalDateProvider;
import com.jpmc.theater.Showing;

import lombok.RequiredArgsConstructor;

import java.math.RoundingMode;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Print schedule using simple text
 */
@RequiredArgsConstructor
public class SimpleTextSchedulePrinter implements SchedulePrinter {

    private static final String MESSAGE_DELIMITER = "===================================================";
    private static final String TIME_PATTERN = "(%s hour%s %s minute%s)";
    private static final String MESSAGE_PATTERN = "%s: %s %s %s $%f.2" + System.lineSeparator();
    private static final int PRICE_PRECISION = 2;

    private final ScheduleService scheduleService;
    private final LocalDateProvider provider;
    private final PricingService pricingService;

    /**
     * Print schedule using simple text
     *
     */
    @Override
    public void print() {
        printSchedule(scheduleService.getSchedule(), provider);
    }

    private void printSchedule(List<Showing> schedule, LocalDateProvider provider) {
        System.out.println(provider.currentDate());
        System.out.println(MESSAGE_DELIMITER);
        schedule.forEach(this::printScheduleRow);
        System.out.println(MESSAGE_DELIMITER);
    }

    private void printScheduleRow(Showing showing) {
        System.out.printf(MESSAGE_PATTERN,
                          showing.getSequenceOfTheDay(),
                          showing.getShowStartTime(),
                          showing.getMovie().getMovieInfo().getTitle(),
                          humanReadableFormat(showing.getMovie().getMovieInfo().getRunningTime()),
                          pricingService.getFullPrice(showing)
                                  .setScale(PRICE_PRECISION, RoundingMode.HALF_DOWN)
                                  .doubleValue());
    }

    private String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format(TIME_PATTERN, hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    private String handlePlural(long value) {
        return value > 1 ? "s" : "";
    }

}
