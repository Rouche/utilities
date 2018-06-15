package org.kitfox.string;

public class SplitPerf {

    private static String stringz = "AA,BBB,CCVV,D";
    private static String stringz2 = "AAAAA";

    private SplitPerf() {
    }

    public static void main(String args[]) throws Exception {

        System.out.println("String1");
        long time = System.currentTimeMillis();
        for (int i = 0; i < 50000000; ++i) {
            for (String s : stringz.split(",")) {
            }
        }
        System.out.println("Split : " + (System.currentTimeMillis() - time));

        time = System.currentTimeMillis();
        substring(stringz);
        System.out.println("Split : " + (System.currentTimeMillis() - time));

        System.out.println("String2");
        time = System.currentTimeMillis();
        for (int i = 0; i < 50000000; ++i) {
            for (String s : stringz2.split(",")) {
            }
        }
        System.out.println("Split : " + (System.currentTimeMillis() - time));

        time = System.currentTimeMillis();
        substring(stringz2);
        System.out.println("Split : " + (System.currentTimeMillis() - time));
    }

    /**
     *
     */
    private static void substring(String the) {
        for (int i = 0; i < 50000000; ++i) {
            int start = 0;
            int index = the.indexOf(",");
            if (index == -1) {
                break;
            }

            while (index != -1) {
                the.substring(start, index);
                start = index + 1;
                index = the.indexOf(",", start);
            }
        }
    }
}