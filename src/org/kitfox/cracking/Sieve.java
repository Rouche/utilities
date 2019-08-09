package org.kitfox.cracking;

import org.junit.Test;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Jean-Francois Larouche (resolutech) on 2019-08-09
 */
@Slf4j
public class Sieve {

    @Test
    public void testDrawLine() {
        int[] result = generatePrimes(100);

        for (int p : result) {
            log.info("Prime: [{}]", p);
        }
    }

    private int[] generatePrimes(int max) {
        int[] primes = new int[max];
        primes[1] = 1;

        int prime = 2;
        while (prime <= max) {
            setPrimes(primes, prime);

            prime = nextPrime(primes, prime);
        }

        return primes;
    }

    private int nextPrime(int[] primes, int prime) {
        int next = prime + 1;
        while (next < primes.length && primes[next] != 0) {
            ++next;
        }
        return next;
    }

    private void setPrimes(int[] primes, int prime) {
        for (int i = prime; i < primes.length; i += prime) {
            primes[i] = prime;
        }
    }

}
