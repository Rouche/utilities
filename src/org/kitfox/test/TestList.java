package org.kitfox.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TestList {

    public static void main(String[] args) {

        Map<String, List<String>> map = new HashMap<>();

        List<String> list = new ArrayList<>();
        list.add("temp1");
        list.add("temp2");
        list.add("temp3");
        list.add("temp4");
        list.add("temp5");
        list.add("temp6");
        list.add("temp7");

        List<Object> copy = Arrays.asList(list.toArray());

        for (Iterator<String> it = list.iterator(); it.hasNext();) {
            String l = it.next();
            if (l.equals("temp4")) {
                it.remove();
                System.out.println("removed " + l);
            }

        }

        System.out.println("List " + list.toString());
        System.out.println("Copy " + copy.toString());

        List<TestWithEnum> aaa = Arrays.asList(TestWithEnum.values());

        // Arrays.asList dont use normal array list and dont support remove.
        aaa.remove(TestWithEnum.A);

    }

    private enum TestWithEnum {
        A,
        B,
        C
    }
}
