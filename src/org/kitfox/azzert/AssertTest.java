/**
 *
 */
package org.kitfox.azzert;

import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author larj15
 */
@Slf4j
public class AssertTest {

    @Test
    public void azzert() throws Exception {
        logDebug(log, "Blah", "Key1", null, "Key2");
    }

    @Test
    public void azzertOK() throws Exception {
        logDebug(log, "BlahOk", "Error because of this", null, "And this", new double[]{1, 2, 3, 4}, "Another", "Quote");
    }


    public void logDebug(Logger log2, String errorMessage, Object... logItems) {
        if (log2.isDebugEnabled()) {
            assert logItems.length % 2 == 0 : "Array must be a combination of subject, Object, subject, Object, ... So a Pair length.";
            ObjectMapper mapper = new ObjectMapper();
            try {
                for (int i = 0; i < logItems.length; i += 2) {
                    if (logItems[i + 1] != null) {
                        log.debug("{}: [{}]", logItems[i], mapper.writeValueAsString(logItems[i + 1]));
                    } else {
                        log.debug("{}: [{}]", logItems[i], null);
                    }
                }
            } catch (IOException e) {
                log.debug(e.getMessage());
                log.debug(errorMessage);
            }
        }
    }
}

