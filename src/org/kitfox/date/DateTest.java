package org.kitfox.date;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTest {

    public static void main(String[] args) {

        Date date = new Date(0);
        System.out.println(date.toString());


        Calendar cal = new GregorianCalendar();
        cal.setTime(Calendar.getInstance().getTime());
        cal.add(Calendar.DAY_OF_MONTH, 1);

        System.out.println(cal.getTime().toString());

        cal = new GregorianCalendar();
        cal.setTime(Calendar.getInstance().getTime());
        cal.add(Calendar.DAY_OF_MONTH, -60);

        System.out.println("Moins 60:" + cal.getTime().toString());


        Date date2 = (new GregorianCalendar(2010, 00, 01, 0, 0, 0)).getTime();

        final Calendar c = Calendar.getInstance();
        c.setTime(date2);
        c.add(Calendar.DAY_OF_YEAR, -1);

        System.out.println("Dois etre 2009 :" + c.getTime());
    }

}
