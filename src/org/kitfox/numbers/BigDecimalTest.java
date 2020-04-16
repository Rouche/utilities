package org.kitfox.numbers;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BigDecimalTest {

    @Test
    public void testToString() {
        log.info(BigDecimal.TEN.toString());
    }

    @Test(expected = ArithmeticException.class)
    public void testToStringScale() {
        BigDecimal nine = new BigDecimal("9");
        BigDecimal.TEN.divide(nine).toString();
    }

    @Test
    public void testToStringScaleOk() {
        BigDecimal nine = new BigDecimal("9");
        log.info(BigDecimal.TEN.divide(nine, 3, RoundingMode.HALF_UP).toString());
    }
}
