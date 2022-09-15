package com.jpmc.theater.domain;

import com.jpmc.theater.test.util.TestDataUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TimeRangeTest {

    private TimeRange timeRange;

    @Test
    void testIsInRangeWhenIsBeforeStartDate() {
        LocalTime startTimeNow = LocalTime.of(15, 45, 30, 0);
        LocalTime startTime = LocalTime.of(15, 45, 30, 1);
        LocalTime endTime = LocalTime.of(16, 45, 30, 1);
        timeRange = new TimeRange(startTime, endTime);

        assertThat(timeRange.isInRange(startTimeNow)).isFalse();
    }

    @Test
    void testIsInRangeWhenIsEqualEndDate() {
        LocalTime startTimeNow = LocalTime.of(16, 45, 30, 0);
        LocalTime startTime = LocalTime.of(15, 45, 30, 1);
        timeRange = new TimeRange(startTime, startTimeNow);

        assertThat(timeRange.isInRange(startTimeNow)).isFalse();
    }

    @Test
    void testIsInRangeWhenIsEqualToStartDate() {
        LocalTime startTimeNow = LocalTime.of(15, 45, 30, 0);
        LocalTime endTime = LocalTime.of(16, 45, 30, 1);
        timeRange = new TimeRange(startTimeNow, endTime);

        assertThat(timeRange.isInRange(startTimeNow)).isTrue();
    }

    @Test
    void testIsInRangeWhenIsInsideRange() {
        LocalTime startTimeNow = LocalTime.of(15, 45, 30, 0);
        LocalTime startTime = LocalTime.of(14, 45, 30, 1);
        LocalTime endTime = LocalTime.of(16, 45, 30, 1);
        timeRange = new TimeRange(startTime, endTime);

        assertThat(timeRange.isInRange(startTimeNow)).isTrue();
    }

    @Test
    void shouldThrowExceptionWhenCreateInstanceWithIncorrectTimes() {
        assertThatThrownBy(() -> new TimeRange(TestDataUtil.TEST_END_TIME, TestDataUtil.TEST_START_TIME))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
