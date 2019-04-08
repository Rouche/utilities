package org.kitfox.sftp;

import org.junit.Test;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * @Author Jean-Francois Larouche (jealar2) on 2019-01-15
 */
public class JSchTest {

    @Test
    public void testJSch() throws JSchException {
        JSch sshClient = new JSch();

        // only for public key authentication
        sshClient.addIdentity("location to private key file");

        Session session = sshClient.getSession("rouche", "127.0.0.1");

        // only for password authentication
        session.setPassword("rouche123");

        session.connect();

        session.disconnect();
    }
}
