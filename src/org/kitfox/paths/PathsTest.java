/**
 *
 */
package org.kitfox.paths;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author larj15
 */
public class PathsTest {

    public static void main(String[] args) throws Exception {

        Path currentRelativePath = Paths.get("");
        Path relativePath = Paths.get("temp/path");
        Path filePath = Paths.get("file.xml");

        currentRelativePath = currentRelativePath.resolve(relativePath).resolve(filePath);
        String s = currentRelativePath.toAbsolutePath().toString();

        System.out.println(s);
    }
}
