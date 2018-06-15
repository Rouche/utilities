package org.kitfox.exam;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jealar2 on 2018-06-07
 * Examen - Test - Entrevue
 */
public class Foo {

    public static void main(String[] args) {
        int x = 1;
        if((4 > x) ^ ((++x + 2) > 3)) x++;
        if((4 > ++x + 1) && !(++x == 4)) x++;
        System.out.println(x);

        problemRuntimeExceptionHidden();
    }

    public static void problemRuntimeExceptionHidden() {
        InputStream is = null;
        try {
            is = init();
            boolean done = false;
            // main loop
            while (!done) {
                // Code here can sometime throw a RuntimeException
                throw new RuntimeException("Ma vraie erreur");
            }
        } finally {
            // there is a bug in this method, causing a
            // NullPointerException to be thrown
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static private InputStream init() {
        return null;
    }


}
