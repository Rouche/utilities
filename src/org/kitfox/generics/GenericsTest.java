/**
 *
 */
package org.kitfox.generics;

import org.junit.Test;

/**
 * @author larj15
 */
public class GenericsTest {

    public static abstract class GenericClass<T> {

        String getClassName() {
            return getClass().getName();
        }
    }

    public static class ConcreteClass extends GenericClass<Long> {

    }

    public static class ConcreteClass2 extends GenericClass<Long> {

    }

    @Test
    public void test() throws Exception {
        println(new ConcreteClass());
        println(new ConcreteClass2());
        System.out.println(new ConcreteClass().getClass().getName());
    }

    private void println(GenericClass g) {
        System.out.println(g.getClassName());
    }
}
