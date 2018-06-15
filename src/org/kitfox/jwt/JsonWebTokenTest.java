/**
 *
 */
package org.kitfox.jwt;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jwk.JsonWebKey;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author larj15
 */
public class JsonWebTokenTest {

    public static void main(String[] args) throws Exception {
        Person person = new Person();
        person.setBirth(LocalDate.of(1960, 01, 01));
        person.setDumb(false);
        person.setMoney(new BigDecimal(100_000));
        person.setName("John Snow");

        ObjectMapper mapper = new ObjectMapper();
        String jSon = mapper.writeValueAsString(person);

        System.out.println(jSon);

        String jwkJson = "{\"kty\":\"oct\",\"k\":\"Fdh9u8rINxFdh9u8rINxAe\"}";
        JsonWebKey jwkey = JsonWebKey.Factory.newJwk(jwkJson);

        JsonWebEncryption jwe = new JsonWebEncryption();
        jwe.setPayload(jSon + jSon + jSon + jSon + jSon + jSon);
        jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.DIRECT);
        jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_GCM);
        jwe.setKey(jwkey.getKey());
        String serializedJwe = jwe.getCompactSerialization();
        System.out.println("Serialized Encrypted JWE (" + serializedJwe.length() + ") : " + serializedJwe);
        if (serializedJwe.length() > 1900) {
            System.out.println("This is too long!!!!");
        }

        jwe = new JsonWebEncryption();
        jwe.setKey(jwkey.getKey());
        jwe.setCompactSerialization(serializedJwe);
        System.out.println("Payload: " + jwe.getPayload());
    }
}
