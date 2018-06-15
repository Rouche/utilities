package org.kitfox.annotation;

import javax.validation.constraints.NotNull;

public class AnnotationTest {

    public static void main(String[] args) {

        testNotNull(null);
    }

    private static void testNotNull(@NotNull Object object) {
        System.out.println("!");
    }

}
