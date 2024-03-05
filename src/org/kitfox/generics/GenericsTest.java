/**
 *
 */
package org.kitfox.generics;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author larj15
 */
@Slf4j
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
        final GenericClass concreteClass = new ConcreteClass();
        info(concreteClass);
        final GenericClass concreteClass2 = new ConcreteClass2();
        info(concreteClass2);

        log.info("Different concrete equals {}", concreteClass.getClass().equals(concreteClass2.getClass()));

        log.info(new ConcreteClass().getClass().getName());



    }

    private void info(GenericClass g) {
        log.info(g.getClassName());
    }
}
