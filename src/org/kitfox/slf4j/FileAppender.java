/**
 *
 */
package org.kitfox.slf4j;

/**
 * @author larj15
 *
 *         Gardé au cas ou on pourrait remettre SLF4J.
 *
 */
public final class FileAppender<E> extends ch.qos.logback.core.FileAppender<E> {

    @Override
    public void setFile(String file) {
        //Get the config root.
        super.setFile(file);
    }
}
