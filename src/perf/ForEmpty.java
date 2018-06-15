package perf;

import java.util.Date;


public class ForEmpty {

    public static void main(String[] args) {
        Date date = new Date();
        String s = "wergwer";
        for(long i = 0; i < 160_000_000L; ++i) {
            s.equals("qwqwd");
        }
        
        System.out.println((new Date()).getTime() - date.getTime());
    }
}
