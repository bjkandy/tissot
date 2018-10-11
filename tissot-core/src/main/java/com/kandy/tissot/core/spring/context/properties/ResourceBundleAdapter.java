package com.kandy.tissot.core.spring.context.properties;

import java.util.*;

public class ResourceBundleAdapter extends Properties {

    private static final long serialVersionUID = -3060271066454942469L;

    public ResourceBundleAdapter(ResourceBundle rb){
        assert (rb instanceof PropertyResourceBundle);
        this.rb = rb;
        @SuppressWarnings("rawtypes")
        Enumeration e = rb.getKeys();
        while (e.hasMoreElements()) {
            Object o = e.nextElement();
            this.put(o, rb.getObject((String) o));
        }
    }

    private ResourceBundle rb = null;

    public ResourceBundle getBundle(String baseName) {
        return ResourceBundle.getBundle(baseName);
    }

    public ResourceBundle getBundle(String baseName, Locale locale) {
        return ResourceBundle.getBundle(baseName, locale);
    }

    public ResourceBundle getBundle(String baseName, Locale locale, ClassLoader loader) {
        return ResourceBundle.getBundle(baseName, locale, loader);
    }

    @SuppressWarnings("rawtypes")
    public Enumeration getKeys() {
        return rb.getKeys();
    }

    public Locale getLocale() {
        return rb.getLocale();
    }

    public Object getObject(String key) {
        return rb.getObject(key);
    }

    public String getString(String key) {
        return rb.getString(key);
    }

    public String[] getStringArray(String key) {
        return rb.getStringArray(key);
    }

    protected Object handleGetObject(String key) {
        return ((PropertyResourceBundle) rb).handleGetObject(key);
    }

}
