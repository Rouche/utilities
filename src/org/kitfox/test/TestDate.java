package org.kitfox.test;

import java.util.Calendar;

public class TestDate {

    public static void main(String[] args) {
        FAQCalendar cal = new FAQCalendar(2009, 0, 1);
        FAQCalendar cal2 = new FAQCalendar(2010, 5, 19);

        long tmp = cal2.diffDayPeriods(cal);
        System.out.println(tmp);
        int days1 = cal.get(Calendar.DAY_OF_YEAR);
        System.out.println(days1);
        int days2 = cal2.get(Calendar.DAY_OF_YEAR);
        System.out.println(days2);
        long diff = cal2.getTimeInMillis() - cal.getTimeInMillis();

        cal.setTimeInMillis(diff);
        cal2.setTimeInMillis(0);
        int m = cal.get(Calendar.MONTH);
        int y = (cal.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12;

        int res = m + y;
        System.out.println(res);

        int t = 0;
        System.out.println(t);
    }
}
