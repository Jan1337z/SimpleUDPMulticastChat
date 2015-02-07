package com.zweifreunde.org.client.localization;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by meier on 07.02.15.
 */
public class ResourceBundleLocalization implements ILocalization
{
    private final ResourceBundle bundle;

    public ResourceBundleLocalization() {
        this.bundle = ResourceBundle.getBundle("com.zweifreunde.etc.localization", Locale.getDefault());
    }

    @Override
    public String getString(String key) {
        return bundle.getString(key);
    }

    @Override
    public String getStringFormat(String key, String... format) {
        String pattern = bundle.getString(key);
        return MessageFormat.format(pattern, format);
    }

}
