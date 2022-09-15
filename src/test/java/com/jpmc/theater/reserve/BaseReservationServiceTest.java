package com.jpmc.theater.reserve;

import com.jpmc.theater.domain.Customer;
import com.jpmc.theater.domain.Reservation;
import com.jpmc.theater.test.util.TestDataUtil;
import com.jpmc.theater.schedule.ScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class BaseReservationServiceTest {

    @Mock
    private ScheduleService scheduleService;

    private BaseReservationService baseReservationService;

    @BeforeEach
    void setUp() {
        Mockito.when(scheduleService.getSchedule())
                .thenReturn(TestDataUtil.TEST_SCHEDULE);
        baseReservationService = new BaseReservationService(scheduleService);
    }

    @Test
    void shouldReserve() {
        Reservation reservation =
                baseReservationService.reserve(new Customer("", ""), TestDataUtil.TEST_SEQUENCE_OF_DAY, 3);
        assertThat(reservation.getShowing())
                .isNotNull();
        assertThat(reservation.getShowing().getSequenceOfTheDay())
                .isEqualTo(TestDataUtil.TEST_SEQUENCE_OF_DAY);
    }

    @Test
    void shouldFailWhenNumberOfTicketsIsSmallerThenOne() {
        assertThatThrownBy(() -> baseReservationService.reserve(new Customer("", ""), TestDataUtil.TEST_SEQUENCE_OF_DAY, 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldFailWhenSequenceNumberIsSmallerThenOne() {
        assertThatThrownBy(() -> baseReservationService.reserve(new Customer("", ""), 0, 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldFailWhenSequenceNumberIsGreaterThenMaximum() {
        assertThatThrownBy(() -> baseReservationService.reserve(new Customer("", ""),
                                                                TestDataUtil.TEST_SCHEDULE.size() + 1,
                                                                4))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
