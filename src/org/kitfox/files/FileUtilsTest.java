/**
 *
 */
package org.kitfox.files;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import lombok.extern.slf4j.Slf4j;

/**
 * @author larj15
 */
@Slf4j
public class FileUtilsTest {

    @Test
    public void testOverride() throws Exception {
        URL url = FileUtilsTest.class.getResource("blah.txt");

        Path path = Paths.get(url.toURI());
        File file = path.toFile();

        File dest = new File("/shared/data/temp/" + file.getName());

        FileUtils.copyFile(file, dest);
        FileUtils.copyFile(file, dest);
        FileUtils.copyFile(file, dest);
        FileUtils.copyFile(file, dest);

        BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
        log.info(attr.lastModifiedTime().toInstant().toString());
        ZonedDateTime now = attr.lastModifiedTime().toInstant().atZone(ZoneId.of("America/Toronto"));
        log.info(now.toString());
    }
}
