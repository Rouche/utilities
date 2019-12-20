/**
 *
 */
package org.kitfox.system;

/**
 * @author Jean-Francois Larouche
 *
 */
public class SystemProperties {

    public static void main(String[] args) {
        System.getProperties().entrySet().stream().sorted(
                (k, v) -> k.getKey().toString().compareTo(v.getKey().toString())
                ).forEach(System.out::println);

        System.out.format("Key [%s] Value: [%d]", "dd", 10);
    }
}
