/**
 *
 */
package org.kitfox.files;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import lombok.extern.slf4j.Slf4j;

/**
 * @author larj15
 */
@Slf4j
public class FileUtilsTest {

    @Test
    public void test() throws Exception {
        URL url = FileUtilsTest.class.getResource("blah.txt");

        File file = Paths.get(url.toURI()).toFile();

        File dest = new File("/shared/data/temp/" + file.getName());

        FileUtils.copyFile(file, dest);
        FileUtils.copyFile(file, dest);
        FileUtils.copyFile(file, dest);
        FileUtils.copyFile(file, dest);

    }
}
