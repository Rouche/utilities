package org.kitfox.sftp;

import org.junit.Test;

import net.schmizz.sshj.SSHClient;

/**
 * @Author Jean-Francois Larouche (jealar2) on 2019-01-15
 */
public class SSHJTest {

    @Test
    public void testSSHJ() throws Exception {
        SSHClient sshClient = new SSHClient();
        sshClient.connect("127.0.0.1", 22);

        // only for public key authentication
        //        sshClient.authPublickey("user", "location to private key file");

        // only for password authentication
        sshClient.authPassword("rouche", "rouche123!");

        net.schmizz.sshj.connection.channel.direct.Session session = sshClient.startSession();

        net.schmizz.sshj.connection.channel.direct.Session.Command command = session.exec("ls");

        System.out.println(command.getExitStatus());

        sshClient.close();
    }
}
