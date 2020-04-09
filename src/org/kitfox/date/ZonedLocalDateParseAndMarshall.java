/**
 *
 */
package org.kitfox.date;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

import org.junit.Test;
import lombok.extern.slf4j.Slf4j;

/**
 * @author larj15
 */
@Slf4j
public class ZonedLocalDateParseAndMarshall {

    @Test
    public void iso8601_Zoned() throws Exception {

        log.info(ZonedDateTime.parse("2020-04-09T18:03:26-05:00[America/Toronto]").toString());
        log.info(ZonedDateTime.parse("2020-04-09T18:03:26-05:00").toString());
        log.info(ZonedDateTime.parse("2020-04-09T18:03:26Z").toString());
    }

    @Test(expected = DateTimeParseException.class)
    public void noDash_Zoned() {
        ZonedDateTime.parse("20200409T180326Z");
    }

    @Test(expected = DateTimeParseException.class)
    public void noTime_Zoned() {
        ZonedDateTime.parse("2020-04-09");
    }

    @Test
    public void local() {
        log.info(LocalDate.parse("2020-04-01").toString());
    }

    @Test(expected = DateTimeParseException.class)
    public void local_Zoned() {
        log.info(LocalDate.parse("2020-04-09T18:03:26+00:00").toString());
    }

    @Test
    public void offset() throws Exception {

        log.info(OffsetDateTime.parse("2020-04-09T18:03:26-05:00").toString());
        log.info(OffsetDateTime.parse("2020-04-09T18:03:26Z").toString());
    }

    @Test(expected = DateTimeParseException.class)
    public void offset_Zoned() {
        log.info(OffsetDateTime.parse("2020-04-09T18:03:26-05:00[America/Toronto]").toString());
    }
}
