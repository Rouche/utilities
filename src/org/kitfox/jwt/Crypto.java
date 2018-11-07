package org.kitfox.jwt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author Jean-Francois Larouche (jealar2) on 2018-11-07
 */
public class Crypto {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
        System.out.println(encoder.encode("7Jh2qS5fdGaaF"));
    }
}
