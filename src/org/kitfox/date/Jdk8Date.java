package org.kitfox.date;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Jdk8Date {

    public static void main(String[] args) {
        ZonedDateTime date = ZonedDateTime.now(ZoneId.of("America/Toronto"));
        System.out.println(date.toString());

        ZonedDateTime date2 = ZonedDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(), 0, 0, 0, date.getZone());

        System.out.println(date2.toString());
    }

}
