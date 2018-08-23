/**
 *
 */
package org.kitfox.jwt;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author larj15
 */
@Slf4j
public class JJWTTest {

    @Test
    public void test() throws Exception {

        byte[] encoded = Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();

        String base64 = new String(Base64.getEncoder().encode(encoded));

        log.debug(base64);

        byte[] decoded = Base64.getDecoder().decode(base64);

        SecretKey key = Keys.hmacShaKeyFor(decoded);


        Map<String, Object> map = new HashMap<>();
        map.put("temp", "a1");
        map.put("temp1", "a2");
        map.put("temp2", "a3");
        map.put("temp3", "a4");
        map.put("temp4", "a5");
        map.put("temp5", "a6");
        map.put("temp6", "a7");
        map.put("temp7", "a8");
        log.debug(Jwts.builder().setSubject("Bob")
                .addClaims(map)
                .signWith(key).compact());

    }
}
