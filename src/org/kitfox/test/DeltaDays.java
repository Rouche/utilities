package org.kitfox.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.TestCase;


/**
 * JUnit Test code which demonstrates why 24 periods are not the equivalent of days in many timezones and test correct ways to calculate delta days. This class
 * will fail the final test, demonstrating the bad method of calculation.
 *
 * @author Paul Hill
 * @since Aug 23, 2004
 */
@SuppressWarnings("deprecation")
public class DeltaDays extends TestCase {
    public DeltaDays(String name) {
        super(name);
    }

    /**
     * a locally useful format in ISO-8601 form.
     */
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 'T' HH:mm:ss.S zzz");

    /**
     * @param cal
     *            - any calendar to grab a date out of
     * @return a formatted string.
     */
    static public String readableDate(Calendar cal) {
        return sdf.format(cal.getTime());
    }

    /**
     * Make sure we are working in a timezone which has the same DLS change dates as the timezone I used to run these tests.
     */
    public void testNorthAmericanDLSDays() {
        // The night before the change over
        FAQCalendar beforeSpringDLS = new FAQCalendar(2004, Calendar.APRIL, 4);
        FAQCalendar afterSpringDLS = new FAQCalendar(2004, Calendar.APRIL, 5);
        String aDate = readableDate(beforeSpringDLS);
        assertEquals(aDate + " should end with ST", "ST", aDate.substring(aDate.length() - 2));
        aDate = readableDate(afterSpringDLS);
        assertEquals(aDate + " should end with DT", "DT", aDate.substring(aDate.length() - 2));
        FAQCalendar beforeAutumnDLS = new FAQCalendar(2004, Calendar.OCTOBER, 30);
        FAQCalendar afterAutumnDLS = new FAQCalendar(2004, Calendar.NOVEMBER, 1);
        aDate = readableDate(beforeAutumnDLS);
        assertEquals(aDate + " should end with DT", "DT", aDate.substring(aDate.length() - 2));
        aDate = readableDate(afterAutumnDLS);
        assertEquals(aDate + " should end with ST", "ST", aDate.substring(aDate.length() - 2));
    }

    /**
     * demonstrate that java.util.GregorianCalendar understands that adding 1 day thru the daylight savings change dates results in the preserving the same
     * clock time.
     */
    public void testCalendarAdd() {
        Calendar start = new GregorianCalendar(2004, Calendar.APRIL, 2, 6, 0, 0);
        assertEquals("2004-04-02 T 06:00:00.0 EST", readableDate(start));
        start.add(Calendar.DAY_OF_MONTH, 1);
        assertEquals("2004-04-03 T 06:00:00.0 EST", readableDate(start));
        start.add(Calendar.DAY_OF_MONTH, 1);
        assertEquals("2004-04-04 T 06:00:00.0 EDT", readableDate(start));

        start = new GregorianCalendar(2004, Calendar.OCTOBER, 30, 6, 0, 0);
        assertEquals("2004-10-30 T 06:00:00.0 EDT", readableDate(start));
        start.add(Calendar.DAY_OF_MONTH, 1);
        assertEquals("2004-10-31 T 06:00:00.0 EST", readableDate(start));
        start.add(Calendar.DAY_OF_MONTH, 1);
        assertEquals("2004-11-01 T 06:00:00.0 EST", readableDate(start));
    }

    /**
     * Now demonstrate three different ways to calculate day differences. 1. subtracting day numbers is one way 2. Another useful idea is that the difference
     * between 8 AM today amd 8 AM tomorrow is 1 day, similarly for all 8 AMs on other days. The difference between before 7:59 AM today and 8:00 AM tomorrow
     * (or later) is more than one day 3. BUT a NOT very useful way is to subtract milliseconds times and divide.
     */
    @SuppressWarnings("unused")
    public void testSomeDeltas() {
        FAQCalendar start = new FAQCalendar(2004, Calendar.APRIL, 1, 6, 0, 0);
        String startStr = readableDate(start);
        FAQCalendar end = new FAQCalendar(start.getTimeInMillis());
        String endStr = readableDate(end);

        end.set(2004, Calendar.APRIL, 2, 6, 0, 0);
        assertDeltaDaysEquals(1, start, end);

        end.set(2004, Calendar.APRIL, 3, 6, 0, 0);
        assertDeltaDaysEquals(2, start, end);

        end.set(2004, Calendar.APRIL, 4, 6, 0, 0); // this will fail
        assertDeltaDaysEquals(3, start, end);
    }

    private void assertDeltaDaysEquals(int expected, FAQCalendar start, FAQCalendar end) {
        assertEquals(expected, end.getJulianDay() - start.getJulianDay());
        assertEquals(expected, start.diffDayPeriods(end));
        assertEquals(expected, start.diff24HourPeriods(end));
    }

    public static void showComparison(FAQCalendar start, FAQCalendar end) {
        System.out.println(readableDate(start) + " to " + readableDate(end) + " is a difference of either " + (end.getJulianDay() - start.getJulianDay())
                + " calendar day(s),  " + start.diffDayPeriods(end) + " day period(s), or " + start.diff24HourPeriods(end) + " 24-hour period(s).");
    }

    public static void main(String[] args) {
        FAQCalendar start = new FAQCalendar(2004, Calendar.APRIL, 1, 6, 0, 0);
        FAQCalendar end = new FAQCalendar(start.getTimeInMillis());

        end.set(2004, Calendar.APRIL, 2, 6, 0, 0);
        showComparison(start, end);
        end.set(2004, Calendar.APRIL, 3, 6, 0, 0);
        showComparison(start, end);
        end.set(2004, Calendar.APRIL, 4, 6, 0, 0);
        showComparison(start, end);
        start.set(2004, Calendar.OCTOBER, 31, 0, 0, 1);
        end.set(2004, Calendar.OCTOBER, 31, 23, 59, 59);
        showComparison(start, end);
    }
}
