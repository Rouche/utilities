package org.kitfox.test;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.Calendar;

public class TestThread extends Thread {

    private int i;

    public class Thread2 implements Runnable {

        @Override
        public void run() {
            System.out.println("Test");

            try {
                this.wait(2000);
            } catch(Exception e) {

            }
        }

    }

    @Override
    public void run() {
        System.out.println("Test");

        try {
            this.wait(2000);
        } catch(Exception e) {

        }
    }

    public static void main(String[] args) {
        TestThread tt = new TestThread();
        Thread2 a = tt.new Thread2();
        FileSystems.getDefault();
        tt.start();
        tt.notify();
    }
}
