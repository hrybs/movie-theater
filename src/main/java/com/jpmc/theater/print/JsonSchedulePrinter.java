package com.jpmc.theater.print;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.theater.domain.Showing;
import com.jpmc.theater.exception.SchedulePrinterInternalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Print schedule using JSON format
 */
@RequiredArgsConstructor
@Slf4j
public class JsonSchedulePrinter implements SchedulePrinter {

    private final ObjectMapper objectWriter;

    /**
     * Print schedule using JSON format
     *
     */
    @Override
    public void print(@Nonnull List<Showing> schedule) {
        print(schedule, objectWriter);
    }

    public String print(@Nonnull List<Showing> schedule, @Nonnull ObjectMapper objectWriter) {
        try {
            String jsonText = objectWriter.writeValueAsString(schedule);
            System.out.println(jsonText);
            return jsonText;
        } catch (JsonProcessingException e) {
            log.error("Error occurred during serialization of schedule: {}", schedule, e);
            throw new SchedulePrinterInternalException("Error occurred during schedule serialization", e); //There are no requirements how to handle any exception
        }
    }

}
