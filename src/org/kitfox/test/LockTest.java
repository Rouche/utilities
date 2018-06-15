package org.kitfox.test;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;

public class LockTest {

    @Test
    public void lockTest() {
        Path path = FileSystems.getDefault().getPath("/");
        try {
            FileStore store = Files.getFileStore(path);
            System.out.println(store.getTotalSpace());
        } catch(IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
