package org.kitfox.performance;

import java.util.Date;

public class AppendPerf {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Date date = new Date();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 1000000; ++i) {
            builder.append(' ');
        }
        System.out.println("Builder append char time : "
                + ((new Date()).getTime() - date.getTime()));

        date = new Date();
        builder = new StringBuilder();
        for (int i = 0; i < 1000000; ++i) {
            builder.append(" ");
        }
        System.out.println("Builder append time : "
                + ((new Date()).getTime() - date.getTime()));

        date = new Date();
        String str = "";
        for (int i = 0; i < 1000000; ++i) {
            str += ' ';
        }
        System.out.println("String append char time : "
                + ((new Date()).getTime() - date.getTime()));
    }

}
