package no.racingteam.bjorn.offsetdatetime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OffsetDateTimeApplicationTests {

    @Test
    void name() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime now2 = LocalDateTime.now();


        ZonedDateTime zonedTimestamp =
                ZonedDateTime.of(2019, 8, 10, 8, 30, 0, 0, ZoneId.of("America/New_York"));
        ZonedDateTime zonedTimestampToCompare =
                ZonedDateTime.of(2019, 8, 10, 14, 0, 0, 0, ZoneId.of("Europe/Berlin"));


    }


    @Test
    public void offsetDateTimeToJsonAndBack() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);

        OffsetDateTime expected = OffsetDateTime.now();
        String actualString = objectMapper.writeValueAsString(expected);
        System.out.println(actualString); // 2018-02-06T08:37:33.557-08:00
        OffsetDateTime actual = objectMapper.readValue(actualString, OffsetDateTime.class);
        assertEquals(expected, actual);
    }

    @Test
    public void offsetDateTimeToStringAndBack() {
        OffsetDateTime expected = OffsetDateTime.now();
        String actualString = expected.toString();
        System.out.println(actualString);
        OffsetDateTime actual = OffsetDateTime.parse(actualString);
        assertEquals(expected, actual);
    }

}
