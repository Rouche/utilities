package org.kitfox.cracking;

import org.junit.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CrackingP51Ex12Test {

    @Test
    public void testPermutations() {
        permutation("abcde");
    }

    void permutation(String str) {
        permutation(str, "");
    }

    private void permutation(String str, String prefix) {
        if(str.length() == 0) {
            log.info(prefix);
        } else {
            for(int i = 0; i < str.length(); ++i) {
                String rem = str.substring(0, i) + str.substring(i + 1);
                permutation(rem, prefix + str.charAt(i));
            }
        }
    }

}
