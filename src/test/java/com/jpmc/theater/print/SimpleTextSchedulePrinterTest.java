package com.jpmc.theater.print;

import com.jpmc.theater.test.util.TestDataUtil;
import com.jpmc.theater.domain.Showing;
import com.jpmc.theater.price.PricingService;
import com.jpmc.theater.util.LocalDateProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class SimpleTextSchedulePrinterTest {

    private int counter = 1;

    @Mock
    private LocalDateProvider provider;

    @Mock
    private PricingService pricingService;

    @InjectMocks
    private SimpleTextSchedulePrinter printer;

    @BeforeEach
    void setUp() {
        when(pricingService.getFullPrice(any(Showing.class)))
                .thenReturn(TestDataUtil.TEST_FULL_PRICE)
                .thenReturn(TestDataUtil.TEST_FULL_PRICE.multiply(BigDecimal.valueOf(TestDataUtil.CHANGE_PRICE_RATE * counter++)))
                .thenReturn(TestDataUtil.TEST_FULL_PRICE.multiply(BigDecimal.valueOf(TestDataUtil.CHANGE_PRICE_RATE * counter++)))
                .thenReturn(TestDataUtil.TEST_FULL_PRICE.multiply(BigDecimal.valueOf(TestDataUtil.CHANGE_PRICE_RATE * counter++)))
                .thenReturn(TestDataUtil.TEST_FULL_PRICE.multiply(BigDecimal.valueOf(TestDataUtil.CHANGE_PRICE_RATE * counter++)));
    }

    @Test
    void shouldPrintScheduleRow() {
        assertThat(printer.printScheduleRow(TestDataUtil.getShowing()))
                .isEqualTo(TestDataUtil.TEST_TEXT_SCHEDULE_ROW);
    }

    @Test
    void shouldPrintSchedule() {
        when(provider.currentDate())
                 .thenReturn(TestDataUtil.TEST_LOCAL_DATE);
        printer.print(TestDataUtil.getSchedule());

        verify(provider).currentDate();
        verify(pricingService, times(5)).getFullPrice(any(Showing.class));
    }

}
