package org.kitfox.jwt;

import java.math.BigDecimal;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.jose4j.base64url.internal.apache.commons.codec.binary.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * http://stackoverflow.com/questions/23561104/how-to-encrypt-and-decrypt-string-with-my-passphrase-in-java-pc-not-
 * mobile-plat
 */
public class SimpleCrypter {

    static String CIPHER_ALGORITHM = "DES";   // successfully tested with AES, Blowfish, DES, DESede, RC2

    public static byte[] encrypt(String toEncrypt, String password)
                    throws Exception {

        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        Key symKey = new SecretKeySpec(password.getBytes(), cipher.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, symKey);
        byte[] inputBytes = toEncrypt.getBytes();
        return cipher.doFinal(inputBytes);
    }

    public static String decrypt(byte[] toDecrypt, String password)
                    throws Exception {

        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        Key symKey = new SecretKeySpec(password.getBytes(), cipher.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, symKey);
        byte[] decrypt = cipher.doFinal(toDecrypt);
        String decrypted = new String(decrypt);
        return decrypted;
    }

    public static void main(String[] args) throws Exception {
        Person person = new Person();
        person.setBirth(null);
        person.setDumb(false);
        person.setMoney(new BigDecimal(100_000));
        person.setName("John Snow");

        ObjectMapper mapper = new ObjectMapper();
        String jSon = mapper.writeValueAsString(person);

        // Encryption
        byte[] encryptedBytes = encrypt(jSon, "1%d3L9gd");
        String base64 = Base64.encodeBase64URLSafeString(encryptedBytes);
        System.out.println("Encoded result (" + base64.length() + ") : " + base64);
        // Decryption
        encryptedBytes = Base64.decodeBase64(base64);
        String decryptedStr = decrypt(encryptedBytes, "1%d3L9gd");
        System.out.println("Decrypted result : " + decryptedStr);
    }
}