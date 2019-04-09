package org.kitfox.sftp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;

import org.junit.Test;
import lombok.extern.slf4j.Slf4j;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.RemoteResourceInfo;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import net.schmizz.sshj.xfer.InMemoryDestFile;

/**
 * @Author Jean-Francois Larouche (jealar2) on 2019-01-15
 */
@Slf4j
public class SSHJTest {

    @Test
    public void testSSHJ() throws Exception {
        SSHClient sshClient = new SSHClient();
        sshClient.addHostKeyVerifier(new PromiscuousVerifier());
//        sshClient.loadKnownHosts();
        sshClient.connect(ConfigUtils.getHost(), 22);

        // only for public key authentication
        //        sshClient.authPublickey("user", "location to private key file");

        // only for password authentication
        sshClient.authPassword(ConfigUtils.getUser(), ConfigUtils.getPassword());


        final SFTPClient sftp = sshClient.newSFTPClient();

        listDirectory(sftp, ".");

        sshClient.close();
    }

    private void listDirectory(SFTPClient sftp, String dir) throws IOException {
        List<RemoteResourceInfo> list = sftp.ls(dir);

        for (RemoteResourceInfo resource : list) {
            if (resource.isDirectory()) {
                log.debug("====================================");
                log.debug("Children of " + resource.getPath());
                log.debug("====================================");
                listDirectory(sftp, resource.getPath());
            } else {
                log.debug(">>>>>>>>>>>>>>>> Processing file " + resource.getPath());
                try {
                    log.debug(readTextFile(sftp, resource.getPath()));
                } catch (IOException e) {
                    log.error("", e);
                }
            }
        }
    }

    /**
     * Reads the contents of a file on the remote node, assuming UTF-8 encoding
     *
     * @param remotePath the full path to the file on the host
     * @return the contents of the file
     */
    public String readTextFile(SFTPClient sftp, String remotePath) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        InMemoryDestFile dest = new InMemoryDestFile() {
            @Override
            public OutputStream getOutputStream() {
                return out;
            }
        };

        sftp.getFileTransfer().download(remotePath, dest);

        return new String(out.toByteArray(), Charset.forName("UTF-8"));
    }
}
