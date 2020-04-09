package org.kitfox.lambda;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Jean-Francois Larouche (resolutech) on 2020-03-24
 */
@Slf4j
public class OptionalTest {
    @Test
    public void testNull() {

        Date original = new Date();

        String dateString = Optional.ofNullable(original)
                .map(date -> date.toInstant())
                .map(instant -> instant.toString())
                .orElse("");

        log.info("Date: [{}]", dateString);

        original = null;
        dateString = Optional.ofNullable(original)
                .map(date -> date.toInstant())
                .map(instant -> instant.toString())
                .orElse("");

        log.info("Date: [{}]", dateString);
    }

}
