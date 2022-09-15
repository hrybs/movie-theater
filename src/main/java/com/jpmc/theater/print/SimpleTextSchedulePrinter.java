package com.jpmc.theater.print;

import com.jpmc.theater.price.PricingService;
import com.jpmc.theater.util.LocalDateProvider;
import com.jpmc.theater.domain.Showing;

import lombok.RequiredArgsConstructor;

import javax.annotation.Nonnull;
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
    private static final String MESSAGE_PATTERN = "%s: %s %s %s $%.2f" + System.lineSeparator();
    private static final int PRICE_PRECISION = 2;

    private final LocalDateProvider provider;
    private final PricingService pricingService;

    /**
     * Print schedule using simple text
     *
     */
    @Override
    public void print(@Nonnull List<Showing> schedule) {
        System.out.println(provider.currentDate());
        System.out.println(MESSAGE_DELIMITER);
        schedule.forEach(this::printScheduleRow);
        System.out.println(MESSAGE_DELIMITER);
    }

    public String printScheduleRow(@Nonnull Showing showing) {
        String scheduleRow = String.format(MESSAGE_PATTERN,
                          showing.getSequenceOfTheDay(),
                          showing.getShowStartTime(),
                          showing.getMovie().getMovieInfo().getTitle(),
                          humanReadableFormat(showing.getMovie().getMovieInfo().getRunningTime()),
                          pricingService.getFullPrice(showing)
                                  .setScale(PRICE_PRECISION, RoundingMode.HALF_DOWN)
                                  .doubleValue());
        System.out.print(scheduleRow);
        return scheduleRow;
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
