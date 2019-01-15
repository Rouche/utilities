package org.kitfox.security;

import javax.crypto.SecretKey;
import java.util.Base64;

import org.junit.Test;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * @Author Jean-Francois Larouche (jealar2) on 2019-01-15
 */
public class JJWT {

    @Test
    public void testGenerateSHA() throws Exception {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        System.out.println(new String(Base64.getEncoder().encode(key.getEncoded())));
    }
}
