package org.kitfox.lambda;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collector;

import org.junit.Test;

public class ArrayLambdaTest {

    @Test
    public void testLambdaAgainstForLoop() {

        Byte[] content = new Byte[10000000];
        byte[] bytesPrim = new byte[10000000];
        for(int i = 0; i < content.length; ++i) {
            content[i] = new Byte((byte)(Math.random() * 127));
        }

        Date date = new Date();
        Arrays.stream(content).collect(Collector.of(
                () -> new int[1],
                (result, value) -> {
                    bytesPrim[result[0]++] = value.byteValue();
                },
                (result1, result2) -> result1
        ));

        System.out.println("Lambda convert : "
                + ((new Date()).getTime() - date.getTime()));


        date = new Date();
        for(int i = 0; i < content.length; ++i) {
            content[i] = content[i].byteValue();
        }

        System.out.println("For convert : "
                + ((new Date()).getTime() - date.getTime()));
    }
}
