package org.kitfox.regex;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.commons.text.StringEscapeUtils;

public class RegExUtils {
    private RegExUtils() {
    }

    public static String find(String s) {
        Pattern pattern = Pattern.compile("(?s)<OutgoingXml[^\\>]*>(.*)</OutgoingXml[^\\>]*>");

        Matcher matcher = pattern.matcher(s);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        InputStream inputStream = (new RegExUtils()).getClass().getResourceAsStream("./test.xml");

        try {
            String s = IOUtils.toString(inputStream, "UTF-8");

            System.out.println(StringEscapeUtils.unescapeXml(find(s)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}