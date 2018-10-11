package com.kandy.tissot.core.spring.context.properties;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class JProperties {

    public final static int BY_PROPERTIES             = 1;
    public final static int BY_RESOURCEBUNDLE         = 2;
    public final static int BY_PROPERTYRESOURCEBUNDLE = 3;
    public final static int BY_CLASS                  = 4;
    public final static int BY_CLASSLOADER            = 5;
    public final static int BY_SYSTEM_CLASSLOADER     = 6;

    public final static Properties loadProperties(final String name, final int type) throws IOException {
        Properties p = new Properties();
        InputStream in = null;
        if (type == BY_PROPERTIES) {
            in = new BufferedInputStream(new FileInputStream(name));
            assert (in != null);
            p.load(in);
        } else if (type == BY_RESOURCEBUNDLE) {
            ResourceBundle rb = ResourceBundle.getBundle(name, Locale.getDefault());
            assert (rb != null);
            p = new ResourceBundleAdapter(rb);
        } else if (type == BY_PROPERTYRESOURCEBUNDLE) {
            in = new BufferedInputStream(new FileInputStream(name));
            assert (in != null);
            ResourceBundle rb = new PropertyResourceBundle(in);
            p = new ResourceBundleAdapter(rb);
        } else if (type == BY_CLASS) {
            assert (JProperties.class.equals(new JProperties().getClass()));
            in = JProperties.class.getResourceAsStream(name);
            assert (in != null);
            p.load(in);
            // return new JProperties().getClass().getResourceAsStream(name);
        } else if (type == BY_CLASSLOADER) {
            assert (JProperties.class.getClassLoader().equals(new JProperties().getClass().getClassLoader()));
            in = JProperties.class.getClassLoader().getResourceAsStream(name);
            assert (in != null);
            p.load(in);
            // return new JProperties().getClass().getClassLoader().getResourceAsStream(name);
        } else if (type == BY_SYSTEM_CLASSLOADER) {
            in = ClassLoader.getSystemResourceAsStream(name);
            assert (in != null);
            p.load(in);
        }

        if (in != null) {
            in.close();
        }
        return p;

    }
}
