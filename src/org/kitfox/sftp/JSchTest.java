package org.kitfox.sftp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Vector;

import org.junit.Test;
import lombok.extern.slf4j.Slf4j;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * @Author Jean-Francois Larouche (jealar2) on 2019-01-15
 */
@Slf4j
public class JSchTest {

    @Test
    public void testJSch() throws Exception {
        JSch sshClient = new JSch();

        // only for public key authentication
        //sshClient.addIdentity("location to private key file");

        Session session = sshClient.getSession(ConfigUtils.getUser(), ConfigUtils.getHost());

        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);

        // only for password authentication
        session.setPassword(ConfigUtils.getPassword());

        ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
        sftpChannel.connect();

        listDirectory(sftpChannel, ".");

        session.disconnect();
    }

    private void listDirectory(ChannelSftp sftp, String dir) throws Exception {
        Vector<ChannelSftp.LsEntry> list = sftp.ls(dir);

//        for (RemoteResourceInfo resource : list) {
//            if (resource.isDirectory()) {
//                log.debug("====================================");
//                log.debug("Children of " + resource.getPath());
//                log.debug("====================================");
//                listDirectory(sftp, resource.getPath());
//            } else {
//                log.debug(">>>>>>>>>>>>>>>> Processing file " + resource.getPath());
//                try {
//                    log.debug(readTextFile(sftp, resource.getPath()));
//                } catch (IOException e) {
//                    log.error("", e);
//                }
//            }
//        }
    }

    /**
     * Reads the contents of a file on the remote node, assuming UTF-8 encoding
     *
     * @param remotePath the full path to the file on the host
     * @return the contents of the file
     */
    public String readTextFile(ChannelSftp sftp, String remotePath) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();


        return new String(out.toByteArray(), Charset.forName("UTF-8"));
    }
}
