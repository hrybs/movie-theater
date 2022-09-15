package com.jpmc.theater;

import com.jpmc.theater.domain.Customer;
import com.jpmc.theater.print.SchedulePrinter;
import com.jpmc.theater.reserve.ReservationService;
import com.jpmc.theater.schedule.ScheduleService;
import com.jpmc.theater.test.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TheaterTest {

    @Mock
    private Customer customer;

    @Mock
    private ReservationService reservationService;

    @Mock
    private SchedulePrinter schedulePrinter;

    @Mock
    private ScheduleService scheduleService;

    @InjectMocks
    private Theater theater;

    @Test
    void shouldReserve() {
        theater.reserve(customer, TestDataUtil.TEST_SEQUENCE_OF_DAY, 1);
        Mockito.verify(reservationService).reserve(customer, TestDataUtil.TEST_SEQUENCE_OF_DAY, 1);

    }

    @Test
    void shouldPrintSchedule() {
        when(scheduleService.getSchedule())
                .thenReturn(TestDataUtil.getSchedule());
        theater.printSchedule();
        Mockito.verify(schedulePrinter).print(TestDataUtil.getSchedule());
    }

    @Test
    void canInvokeMainWithoutExceptions() {
        Theater.main();
    }

}
