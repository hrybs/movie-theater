package com.jpmc.theater;

import com.jpmc.theater.config.DiscountConfig;
import com.jpmc.theater.config.SpecialCode;
import com.jpmc.theater.domain.Customer;
import com.jpmc.theater.domain.Movie;
import com.jpmc.theater.domain.MovieInfo;
import com.jpmc.theater.domain.Reservation;
import com.jpmc.theater.price.BasePricingService;
import com.jpmc.theater.price.PricingService;
import com.jpmc.theater.price.rule.DiscountRule;
import com.jpmc.theater.print.CombineSchedulePrinter;
import com.jpmc.theater.print.JsonSchedulePrinter;
import com.jpmc.theater.print.SchedulePrinter;
import com.jpmc.theater.print.SimpleTextSchedulePrinter;
import com.jpmc.theater.reserve.BaseReservationService;
import com.jpmc.theater.reserve.ReservationService;
import com.jpmc.theater.schedule.BaseScheduleService;
import com.jpmc.theater.schedule.ScheduleService;
import com.jpmc.theater.util.LocalDateProvider;
import com.jpmc.theater.util.ObjectMapperProvider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Theater Facade
 * The class is thread safe
 */
@Getter
@RequiredArgsConstructor
public class Theater {

    private final ReservationService reservationService;
    private final ScheduleService scheduleService;
    private final SchedulePrinter schedulePrinter;

    /**
     * Reserve ticket
     *
     * @param customer customer
     * @param sequence sequence
     * @param numberOfTickets number of tickets
     * @return reservation
     */
    public Reservation reserve(Customer customer, int sequence, int numberOfTickets) {
        return reservationService.reserve(customer, sequence, numberOfTickets);
    }

    /**
     * Print Schedule
     */
    public void printSchedule() {
        schedulePrinter.print(scheduleService.getSchedule());
    }

    public static void main(String... args) {
        Theater theater = createTheater();
        theater.printSchedule();
    }

    private static Theater createTheater() {
        ScheduleService scheduleService = new BaseScheduleService(LocalDateProvider.getInstance(), getTimeToMovieMap());
        Set<DiscountRule> discountRules = DiscountConfig.getDiscountRules();
        PricingService pricingService = new BasePricingService(discountRules);
        ReservationService reservationService = new BaseReservationService(scheduleService);
        SchedulePrinter combineSchedulePrinter = createSchedulePrinter(scheduleService, pricingService);

        return new Theater(reservationService, scheduleService, combineSchedulePrinter);
    }

    private static SortedMap<LocalTime, Movie> getTimeToMovieMap() {
        MovieInfo spiderManMovieInfo = new MovieInfo("Spider-Man: No Way Home", Duration.ofMinutes(90));
        MovieInfo turningRedMovieInfo = new MovieInfo("Turning Red", Duration.ofMinutes(85));
        MovieInfo theBatManMovieInfo = new MovieInfo("The Batman", Duration.ofMinutes(95));
        Movie spiderMan = new Movie(spiderManMovieInfo, BigDecimal.valueOf(12.5), SpecialCode.SUPER_SPECIAL);
        Movie turningRed = new Movie(turningRedMovieInfo, BigDecimal.valueOf(11));
        Movie theBatMan = new Movie(theBatManMovieInfo, BigDecimal.valueOf(9));

        SortedMap<LocalTime, Movie> timeToMovieMap = new TreeMap<>();
        timeToMovieMap.put(LocalTime.of(9, 0), turningRed);
        timeToMovieMap.put(LocalTime.of(11, 0), spiderMan);
        timeToMovieMap.put(LocalTime.of(12, 50), theBatMan);
        timeToMovieMap.put(LocalTime.of(14, 30), turningRed);
        timeToMovieMap.put(LocalTime.of(16, 10), spiderMan);
        timeToMovieMap.put(LocalTime.of(17, 50), theBatMan);
        timeToMovieMap.put(LocalTime.of(19, 30), turningRed);
        timeToMovieMap.put(LocalTime.of(21, 10), spiderMan);
        timeToMovieMap.put(LocalTime.of(23, 0), theBatMan);
        return timeToMovieMap;
    }

    private static SchedulePrinter createSchedulePrinter(ScheduleService scheduleService, PricingService pricingService) {
        SchedulePrinter simpleTextSchedulePrinter = new SimpleTextSchedulePrinter(LocalDateProvider.getInstance(), pricingService);
        SchedulePrinter jsonSchedulePrinter = new JsonSchedulePrinter(ObjectMapperProvider.getDefaultObjectMapper());
        List<SchedulePrinter> schedulePrinters = List.of(simpleTextSchedulePrinter, jsonSchedulePrinter);
        return new CombineSchedulePrinter(schedulePrinters);
    }

}
