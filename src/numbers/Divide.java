package numbers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Divide {

    public static void main(String[] args) {
        
        BigDecimal b1 = new BigDecimal("179");
        BigDecimal b2 = new BigDecimal("60");
        
        System.out.println("Value: " + divide(b1, b2));
        
    }

    private static BigDecimal divide(BigDecimal b1, BigDecimal b2) {
    	
    	Map<List<String>, String> s = new HashMap<>();
    	
    	int data = 0b0101011001;
    	
    	long a = 0b1L;
    	
        return b1.divide(b2, 2, RoundingMode.HALF_UP);
    }
}
