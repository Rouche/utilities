/**
 *
 */
package org.kitfox.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import lombok.extern.slf4j.Slf4j;

/**
 * @author larj15
 */
@Slf4j
public class LocalDateOf {

    @Test
    public void of() {
        Date date = (new GregorianCalendar(2010, 0, 1)).getTime();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        LocalDate local = LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));


        log.info("Local: {}", local.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
    }
}
