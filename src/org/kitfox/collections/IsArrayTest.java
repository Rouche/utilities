package org.kitfox.collections;

import java.util.*;

import org.junit.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IsArrayTest {

    @Test
    public void testIsArray() {
        log.info("List with ArrayList [{}]", List.class.isAssignableFrom(ArrayList.class));
    }

    @Test
    public void testIsCollection() {
        log.info("Collection with ArrayList [{}]", Collection.class.isAssignableFrom(ArrayList.class));
    }

    @Test
    public void testIsMap() {
        log.info("Map with HashMap [{}]", Map.class.isAssignableFrom(HashMap.class));
    }
}
