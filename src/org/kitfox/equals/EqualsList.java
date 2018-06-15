package org.kitfox.equals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EqualsList {

    /**
     * @author larj15
     */
    public enum MyEnum  {

    }

    public static void main(String[] args) {
        List<BigDecimal> l = new ArrayList<BigDecimal>();
        List<String> m = new ArrayList<String>();
        System.out.println(l.equals(m));
    }

}
