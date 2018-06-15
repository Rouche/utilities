package org.kitfox.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchUtils {
    private MatchUtils() {
    }

    public static void main(String args[]) throws Exception {
        System.out.println("2.456_-79012".matches("(\\w|-|\\.){1,12}"));

        System.out.println("A9A9A9".matches("\\p{Alpha}\\d\\p{Alpha}\\d\\p{Alpha}\\d"));
        System.out.println("99999".matches("\\d{3,9}"));

        System.out.println("we$fwef  ___ rthterthrth ----".replaceAll("\\$| |_", ""));

        System.out.println("3425 %".replaceAll("\\%| ", ""));

        System.out.println("234525".replaceAll("([0-9]{0,2})([0-9]{3})", "$1 $2"));

        Pattern p = Pattern.compile("^$|\\w+\\.\\w+\\.\\w+(-SNAPSHOT)?");
        Matcher m = p.matcher("1.x.y-SNAPSHOT");
        System.out.println(m.matches());
        m = p.matcher("1.x.y-SNAP");
        System.out.println(m.matches());
        m = p.matcher("1.y-SNAPSHOT");
        System.out.println(m.matches());
        m = p.matcher("1.x.y");
        System.out.println(m.matches());
        m = p.matcher("");
        System.out.println(m.matches());
    }
}