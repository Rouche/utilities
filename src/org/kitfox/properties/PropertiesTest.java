package org.kitfox.properties;

import java.io.InputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class PropertiesTest {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {

        new PropertiesTest().process();

    }

    private void process() {
        ResourceBundle bundle = ResourceBundle.getBundle("org.kitfox.properties.test", new UTF8Control());
        try {
            System.out.println(bundle.getString("test1"));
            System.out.println(bundle.getString("test2"));
            System.out.println(bundle.getString("test3"));
        } catch(Exception e) {
            e.printStackTrace();
        }

        InputStream is = this.getClass().getResourceAsStream("test.utf8.properties");
        try {
            ResourceBundle bundle2 = new PropertyResourceBundle(is);
            System.out.println(bundle2.getString("test1"));
            System.out.println(bundle2.getString("test2"));
            System.out.println(bundle2.getString("test3"));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
