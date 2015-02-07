package com.zweifreunde.org.client.localization;

/**
 * Created by meier on 07.02.15.
 */
public interface ILocalization {
    String getString(String key);
    String getStringFormat(String key, String... format);
}
