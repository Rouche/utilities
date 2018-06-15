package org.kitfox.string;

import java.util.Arrays;
import java.util.StringTokenizer;

public class SplitUtils {
    private SplitUtils() {
    }

    public static String[] split(String s, String reg) {
        return s.split(reg);
    }

    public static void main(String args[]) throws Exception {
        System.out.println(Arrays.toString(split("||", "\\|")));
        System.out.println(Arrays.toString(split("a|2|3", "\\|")));
        System.out.println(Arrays.toString(split("a|a|", "\\|")));
        System.out.println(Arrays.toString(split("|a|a", "\\|")));
        
        StringTokenizer tokenizer = new StringTokenizer("1|2||3", "|");
        System.out.println(tokenizer.countTokens());
    }
}