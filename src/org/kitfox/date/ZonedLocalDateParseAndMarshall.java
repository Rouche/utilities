/**
 *
 */
package org.kitfox.date;

import java.time.*;
import java.time.format.DateTimeParseException;

import org.junit.Test;
import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author larj15
 */
@Slf4j
public class ZonedLocalDateParseAndMarshall {

    @Test
    public void iso8601_Zoned() throws Exception {

        log.info("ZonedDateTime from zone: {}", ZonedDateTime.parse("2020-04-09T18:03:26-05:00[America/Toronto]").toString());
        log.info("ZonedDateTime from -5: {}", ZonedDateTime.parse("2020-04-09T18:03:26-05:00").toString());
        log.info("ZonedDateTime from Z: {}", ZonedDateTime.parse("2020-04-09T18:03:26Z").toString());
    }

    @Test
    public void local() {
        log.info("LocalDate: {}", LocalDate.parse("2020-04-01").toString());
    }

    @Test
    public void offset() {

        log.info("Offset from -5: {}", OffsetDateTime.parse("2020-04-09T18:03:26-05:00").toString());
        log.info("Offset from Z: {}", OffsetDateTime.parse("2020-04-09T18:03:26Z").toString());
    }

    @Test
    public void localDateTime() {
        log.info("LocalDateTime: {}", LocalDateTime.parse("2020-04-03T18:03:26").toString());
    }

    @Test
    public void instantToOffset() {
        Instant instant = Instant.now();
        String s = instant.toString();
        String offset = OffsetDateTime.parse(s).toString();

        log.info("Offset from Instant: [{}]", offset);

        assertThat(s).isEqualTo(offset);
    }

    @Test(expected = DateTimeParseException.class)
    public void offsetToInstant() {
        OffsetDateTime offset = OffsetDateTime.now();
        String s = offset.toString();
        log.info("Offset now: [{}]", s);
        String instant = Instant.parse(s).toString();

        log.info("Instant from Offset: {}", instant);

        assertThat(s).isEqualTo(OffsetDateTime.parse(s).toString());
    }

    @Test(expected = DateTimeParseException.class)
    public void noDash_NoOffset() {
        ZonedDateTime.parse("2020-04-09T18:03:26");
    }

    @Test(expected = DateTimeParseException.class)
    public void noDash_Zoned() {
        ZonedDateTime.parse("20200409T180326Z");
    }

    @Test(expected = DateTimeParseException.class)
    public void noTime_Zoned() {
        ZonedDateTime.parse("2020-04-09");
    }

    @Test(expected = DateTimeParseException.class)
    public void local_Zoned() {
        log.info(LocalDate.parse("2020-04-09T18:03:26+00:00").toString());
    }

    @Test(expected = DateTimeParseException.class)
    public void offset_Zoned() {
        log.info(OffsetDateTime.parse("2020-04-09T18:03:26-05:00[America/Toronto]").toString());
    }

    @Test(expected = DateTimeParseException.class)
    public void offset_NoOffset() {
        log.info(OffsetDateTime.parse("2020-04-09T18:03:26").toString());
    }

    @Test(expected = DateTimeParseException.class)
    public void localDateTime_Offset() {
        log.info(LocalDateTime.parse("2020-04-03T18:03:26-5:00").toString());
    }

    @Test(expected = DateTimeParseException.class)
    public void localDateTime_NoTime() {
        log.info(LocalDateTime.parse("2020-04-03").toString());
    }
}
