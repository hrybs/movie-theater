package com.jpmc.theater.schedule;

import com.jpmc.theater.domain.Movie;
import com.jpmc.theater.domain.Showing;
import com.jpmc.theater.test.util.TestDataUtil;
import com.jpmc.theater.util.LocalDateProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class BaseScheduleServiceTest {

    @Mock
    private LocalDateProvider dateProvider;

    private BaseScheduleService baseScheduleService;

    @BeforeEach
    void setUp() {
        Mockito.when(dateProvider.currentDate())
                .thenReturn(TestDataUtil.TEST_LOCAL_DATE);
        baseScheduleService = new BaseScheduleService(dateProvider, TestDataUtil.timeToMovieMap());
    }

    @Test
    void shouldGetSchedule() {
        assertThat(baseScheduleService.getSchedule())
                .isEqualTo(TestDataUtil.getSchedule());
    }

    @Test
    void shouldRefreshGetSchedule() {
        SortedMap<LocalTime, Movie> timeToMovieMap = TestDataUtil.timeToMovieMap();
        timeToMovieMap.remove(TestDataUtil.START_DATE_TIME_5.toLocalTime());
        baseScheduleService.refresh(timeToMovieMap);
        List<Showing> expectedSchedule = new ArrayList<>(TestDataUtil.getSchedule());
        expectedSchedule.remove(expectedSchedule.size() - 1);
        assertThat(baseScheduleService.getSchedule())
                .isEqualTo(expectedSchedule);
    }

}
