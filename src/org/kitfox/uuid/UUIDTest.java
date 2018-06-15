package org.kitfox.uuid;

import java.util.Date;
import java.util.UUID;

public class UUIDTest {

    private UUIDTest() {
    }

    public static void main(String args[]) throws Exception {

        System.out.println(UUID.randomUUID().toString());

        System.out.println(new Date().getTime());
    }
}