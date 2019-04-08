package org.kitfox.sftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.vfs2.*;
import org.junit.Test;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author Jean-Francois Larouche (jealar2) on 2019-01-15
 */
@Slf4j
public class VFSTest {

    @Test
    public void testCommonsVFS() throws Exception {
        FileSystemManager fileSystemManager = VFS.getManager();

        // only for public key authentication
        //        SftpFileSystemConfigBuilder sftpConfigBuilder = SftpFileSystemConfigBuilder.getInstance();
        //        FileSystemOptions opts = new FileSystemOptions();
        //        sftpConfigBuilder.setIdentities(opts, new File[]{privateKey.toFile()});
        //        String connectionUrl = String.format("sftp://%s@%s", user, host);

        // only for password authentication
        String connectionUrl = String.format("sftp://%s:%s@%s", "rouche", "rouche123", "127.0.0.1");

        // Connection set-up
        FileObject remoteRootDirectory = fileSystemManager.resolveFile(connectionUrl);

        Path path = Paths.get("/shared/data");

        listFilesVFS(remoteRootDirectory, path);

        remoteRootDirectory.close();
    }

    private void listFilesVFS(FileObject remoteRootDirectory, Path path) throws IOException {
        FileObject[] children = remoteRootDirectory.getChildren();
        log.debug("====================================");
        log.debug("Children of " + remoteRootDirectory.getName().getURI());
        log.debug("====================================");
        for (int i = 0; i < children.length; i++) {
            if(children[i].getType() == FileType.FOLDER) {
                listFilesVFS(children[i], path);
            } else {
                log.debug(">>>>>>>>>>>>>>>> Processing file " + children[i].getName().getBaseName());
                FileContent content = children[i].getContent();

                InputStreamReader reader = new InputStreamReader(content.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(reader);
                StringBuffer stringBuffer = new StringBuffer();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
                reader.close();
            }
        }
    }
}
