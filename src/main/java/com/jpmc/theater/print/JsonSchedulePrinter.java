package com.jpmc.theater.print;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jpmc.theater.exception.SchedulePrinterInternalException;
import com.jpmc.theater.price.PricingService;
import com.jpmc.theater.schedule.ScheduleService;
import com.jpmc.theater.util.LocalDateProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Print schedule using JSON format
 */
@RequiredArgsConstructor
@Slf4j
public class JsonSchedulePrinter implements SchedulePrinter {

    private final ObjectWriter objectWriter = new ObjectMapper().writer();

    private final ScheduleService scheduleService;
    private final LocalDateProvider provider;
    private final PricingService pricingService;

    /**
     * Print schedule using JSON format
     *
     */
    @Override
    public void print() {
        try {
            System.out.println(objectWriter.writeValueAsString(scheduleService.getSchedule()));
        } catch (JsonProcessingException e) {
            log.error("Error occurred during serialization of schedule: {}", scheduleService.getSchedule(), e);
            throw new SchedulePrinterInternalException("Error occurred during schedule serialization", e); //There are no requirements how to handle any exception
        }
    }
}
