package org.kitfox.sftp;

import java.util.ResourceBundle;

/**
 * @Author Jean-Francois Larouche (jealar2) on 2019-04-09
 */
public class ConfigUtils {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("org.kitfox.sftp.config");

    public static String getHost() {
        return bundle.getString("ftp.host");
    }

    public static String getUser() {
        return bundle.getString("ftp.user");
    }

    public static String getPassword() {
        return bundle.getString("ftp.password");
    }

    public static boolean getConsoleContent() {
        return Boolean.valueOf(bundle.getString("ftp.console.content"));
    }
}
