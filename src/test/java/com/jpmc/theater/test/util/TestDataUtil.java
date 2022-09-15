package com.jpmc.theater.test.util;
import com.jpmc.theater.config.SpecialCode;
import com.jpmc.theater.domain.Movie;
import com.jpmc.theater.domain.MovieInfo;
import com.jpmc.theater.domain.Showing;
import com.jpmc.theater.domain.TimeRange;
import com.jpmc.theater.price.domain.Discount;
import com.jpmc.theater.price.domain.ValueDiscount;
import com.jpmc.theater.price.rule.DiscountRule;
import com.jpmc.theater.price.rule.DateOfMonthDiscountRule;
import com.jpmc.theater.price.rule.TimeRangeDiscountRule;
import com.jpmc.theater.price.rule.SequenceDiscountRule;
import com.jpmc.theater.price.rule.SpecialMovieDiscountRule;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@UtilityClass
public final class TestDataUtil {

    public static final LocalTime TEST_START_TIME = LocalTime.of(21, 25);

    public static final LocalTime TEST_END_TIME = LocalTime.of(23, 59);

    public static final Duration TEST_LOCAL_DURATION = Duration.ofMinutes(97);

    public static final LocalDateTime START_DATE_TIME = LocalDateTime.of(2022, 10, 5, 22, 30);

    public static final LocalDateTime START_DATE_TIME_2 = LocalDateTime.of(2022, 10, 5, 22, 40);

    public static final LocalDateTime START_DATE_TIME_3 = LocalDateTime.of(2022, 10, 5, 22, 50);

    public static final LocalDateTime START_DATE_TIME_4 = LocalDateTime.of(2022, 10, 5, 23, 10);

    public static final LocalDateTime START_DATE_TIME_5 = LocalDateTime.of(2022, 10, 5, 23, 20);

    public static final LocalDate TEST_LOCAL_DATE = LocalDate.of(2022, 10, 5);

    public static final BigDecimal TEST_FULL_PRICE = BigDecimal.valueOf(324);

    public static final int TEST_SEQUENCE_OF_DAY = 2;

    public static final ValueDiscount DATE_VALUE_DISCOUNT = new ValueDiscount(BigDecimal.valueOf(4));

    public static final ValueDiscount TIME_RANGE_VALUE_DISCOUNT = new ValueDiscount(BigDecimal.valueOf(7));

    public static final ValueDiscount SEQUENCE_VALUE_DISCOUNT = new ValueDiscount(BigDecimal.valueOf(2));

    public static final ValueDiscount SPECIAL_VALUE_DISCOUNT = new ValueDiscount(BigDecimal.valueOf(6));

    public static final String TEST_MOVIE_TITLE = "Super Movie 2";

    public static final String TEST_TEXT_SCHEDULE_ROW = "2: 2022-10-05T22:30 Super Movie 20 (1 hour 37 minutes) $324.00" + System.lineSeparator();

    public static final double CHANGE_PRICE_RATE = 1.37;

    public static final long CHANGE_DURATION_RATE = 15L;

    public static final List<Showing> TEST_SCHEDULE = TestDataUtil.getSchedule();

    public static Set<DiscountRule> getDiscountRules() {
        return Set.of(
                new DateOfMonthDiscountRule(getDateDiscountMap()),
                new TimeRangeDiscountRule(getTimeRangeDiscountMap()),
                new SequenceDiscountRule(getSequenceDiscountMap()),
                new SpecialMovieDiscountRule(getSpecialDiscountMap())
        );
    }

    public static Map<Integer, Discount> getDateDiscountMap() {
        return Map.of(
                TEST_LOCAL_DATE.getDayOfMonth(), DATE_VALUE_DISCOUNT,
                LocalDate.now().getDayOfMonth(), new ValueDiscount(BigDecimal.valueOf(7))
        );
    }

    public static Map<TimeRange, Discount> getTimeRangeDiscountMap() {
        return Map.of(
                getTimeRange(), TIME_RANGE_VALUE_DISCOUNT,
                new TimeRange(LocalTime.now().plusHours(1), LocalTime.now().plusHours(2)), new ValueDiscount(BigDecimal.valueOf(7))
        );
    }

    public static Map<Integer, Discount> getSequenceDiscountMap() {
        return Map.of(
                TEST_SEQUENCE_OF_DAY, SEQUENCE_VALUE_DISCOUNT,
                7, new ValueDiscount(BigDecimal.valueOf(3))
        );
    }

    public static Map<SpecialCode, Discount> getSpecialDiscountMap() {
        return Map.of(
                SpecialCode.SUPER_SPECIAL, SPECIAL_VALUE_DISCOUNT
        );
    }

    public static List<Showing> getSchedule() {
        return List.of(
                getShowing(1, START_DATE_TIME),
                getShowing(2, START_DATE_TIME_2),
                getShowing(3, START_DATE_TIME_3),
                getShowing(4, START_DATE_TIME_4),
                getShowing(5, START_DATE_TIME_5)
        );
    }

    public static SortedMap<LocalTime, Movie> timeToMovieMap() {
        SortedMap<LocalTime, Movie> timeToMovieMap = new TreeMap<>();
        timeToMovieMap.put(START_DATE_TIME.toLocalTime(), getMovie(1));
        timeToMovieMap.put(START_DATE_TIME_2.toLocalTime(), getMovie(2));
        timeToMovieMap.put(START_DATE_TIME_3.toLocalTime(), getMovie(3));
        timeToMovieMap.put(START_DATE_TIME_4.toLocalTime(), getMovie(4));
        timeToMovieMap.put(START_DATE_TIME_5.toLocalTime(), getMovie(5));
        return timeToMovieMap;
    }

    public static TimeRange getTimeRange() {
        return new TimeRange(TEST_START_TIME, TEST_END_TIME);
    }

    public static Showing getShowing() {
        return new Showing(getMovie(), TEST_SEQUENCE_OF_DAY, START_DATE_TIME);
    }

    public static Showing getShowing(int sequence, LocalDateTime startTime) {
        return new Showing(getMovie(sequence), sequence, startTime);
    }

    public static Movie getMovie() {
        return new Movie(getMovieInfo(0), TEST_FULL_PRICE, SpecialCode.SUPER_SPECIAL);
    }

    public static Movie getMovie(int sequence) {
        return new Movie(getMovieInfo(sequence), TEST_FULL_PRICE.multiply(BigDecimal.valueOf(sequence * CHANGE_PRICE_RATE)), SpecialCode.SUPER_SPECIAL);
    }

    public static MovieInfo getMovieInfo(int sequence) {
        return new MovieInfo(TEST_MOVIE_TITLE + sequence, TEST_LOCAL_DURATION.plusMinutes(sequence * CHANGE_DURATION_RATE));
    }
}
