package org.kitfox.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.upperCase;

public class TestRegex {

    private static final Pattern FILIERE_PATTERN = Pattern.compile("[A-Z]+\\.([A-Z][0-9]+)\\.[A-Z\\.]+");

    private TestRegex() {
    }

    public static void main(String[] args) throws Exception {
        Matcher matcher = FILIERE_PATTERN.matcher(upperCase("http://xxxx.z01.dev.xxxx.xx:8082/"));
        System.out.println(matcher.find() ? matcher.group(1) : "");
    }
}