package com.jpmc.theater.print;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonassert.JsonAssert;
import com.jayway.jsonassert.JsonAsserter;
import com.jpmc.theater.test.util.TestDataUtil;
import com.jpmc.theater.exception.SchedulePrinterInternalException;
import com.jpmc.theater.util.ObjectMapperProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class JsonSchedulePrinterTest {

    @Mock
    private ObjectMapper objectMapper;

    private JsonSchedulePrinter printer;

    @Test
    void shouldPrintScheduleWithEmbeddedMapper() throws JsonProcessingException {
        printer = new JsonSchedulePrinter(objectMapper);
        printer.print(TestDataUtil.getSchedule());
        verify(objectMapper).writeValueAsString(TestDataUtil.getSchedule());
    }

    @Test
    void shouldPrintScheduleWithExternalMapper() {
        printer = new JsonSchedulePrinter(ObjectMapperProvider.getDefaultObjectMapper());
        String jsonSchedule =
                printer.print(TestDataUtil.getSchedule(), ObjectMapperProvider.getDefaultObjectMapper());
        JsonAsserter asserter = JsonAssert.with(jsonSchedule);
        asserter.assertEquals("$.length()", 5);
        asserter.assertEquals("$.[0].sequenceOfTheDay", 1);
        asserter.assertEquals("$.[0].showStartTime", "2022-10-05T22:30:00");
        asserter.assertEquals("$.[0].movie.ticketPrice", 443.88);
        asserter.assertEquals("$.[0].movie.specialCode", "SUPER_SPECIAL");
        asserter.assertEquals("$.[0].movie.movieInfo.title", "Super Movie 21");
        asserter.assertEquals("$.[0].movie.movieInfo.runningTime", 6720.0);
        asserter.assertEquals("$.[0].movie.movieInfo.description", "");

        asserter.assertEquals("$.[1].sequenceOfTheDay", 2);
        asserter.assertEquals("$.[1].showStartTime", "2022-10-05T22:40:00");
        asserter.assertEquals("$.[1].movie.ticketPrice", 887.76);
        asserter.assertEquals("$.[1].movie.specialCode", "SUPER_SPECIAL");
        asserter.assertEquals("$.[1].movie.movieInfo.title", "Super Movie 22");
        asserter.assertEquals("$.[1].movie.movieInfo.runningTime", 7620.0);
        asserter.assertEquals("$.[1].movie.movieInfo.description", "");

        asserter.assertEquals("$.[2].sequenceOfTheDay", 3);
        asserter.assertEquals("$.[2].showStartTime", "2022-10-05T22:50:00");
        asserter.assertEquals("$.[2].movie.ticketPrice", 1331.64);
        asserter.assertEquals("$.[2].movie.specialCode", "SUPER_SPECIAL");
        asserter.assertEquals("$.[2].movie.movieInfo.title", "Super Movie 23");
        asserter.assertEquals("$.[2].movie.movieInfo.runningTime", 8520.0);
        asserter.assertEquals("$.[2].movie.movieInfo.description", "");

        asserter.assertEquals("$.[3].sequenceOfTheDay", 4);
        asserter.assertEquals("$.[3].showStartTime", "2022-10-05T23:10:00");
        asserter.assertEquals("$.[3].movie.ticketPrice", 1775.52);
        asserter.assertEquals("$.[3].movie.specialCode", "SUPER_SPECIAL");
        asserter.assertEquals("$.[3].movie.movieInfo.title", "Super Movie 24");
        asserter.assertEquals("$.[3].movie.movieInfo.runningTime", 9420.0);
        asserter.assertEquals("$.[3].movie.movieInfo.description", "");

        asserter.assertEquals("$.[4].sequenceOfTheDay", 5);
        asserter.assertEquals("$.[4].showStartTime", "2022-10-05T23:20:00");
        asserter.assertEquals("$.[4].movie.ticketPrice", new BigDecimal("2219.4000000000001620"));
        asserter.assertEquals("$.[4].movie.specialCode", "SUPER_SPECIAL");
        asserter.assertEquals("$.[4].movie.movieInfo.title", "Super Movie 25");
        asserter.assertEquals("$.[4].movie.movieInfo.runningTime", 10320.0);
        asserter.assertEquals("$.[4].movie.movieInfo.description", "");
    }

    @Test
    void shouldFailWhenUnParsableObject() throws JsonProcessingException {
        printer = new JsonSchedulePrinter(objectMapper);
        doThrow(JsonProcessingException.class)
                .when(objectMapper).writeValueAsString(TestDataUtil.getSchedule());

        assertThatThrownBy(() -> printer.print(TestDataUtil.getSchedule()))
                .isInstanceOf(SchedulePrinterInternalException.class);
    }

}
