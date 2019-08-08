package org.kitfox.cracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PermutationTest {

    /**
     * Page 71 Optimize and Solve
     */
    @Test
    public void testPermutarionsMerge() {
        permutationMerge("abcde");
    }

    private void permutationMerge(String str) {
        if(str == null || str.length() == 0) {
            return;
        }
        List<String> result = permutationMergeInternal(str);

        for(String s : result) {
            log.info(s);
        }
    }

    private List<String> permutationMergeInternal(String str) {
        if(str.length() == 1) {
            return Arrays.asList( new String[]{ str });
        }

        int index = str.length() - 1;
        String chopped = str.substring(index);
        List<String> permuted = permutationMergeInternal(str.substring(0, index));

        //insert chopped in every places of the returning list;
        List<String> newList = new ArrayList<>();
        StringBuilder b = new StringBuilder();
        for(String current : permuted) {
            for(int i = 0; i < current.length() + 1; ++i) {
                b.replace(0, b.length(), current);
                b.insert(i, chopped);
                newList.add(b.toString());
            }
        }
        return newList;
    }

    /**
     * Page 51, Example 12
     */
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
