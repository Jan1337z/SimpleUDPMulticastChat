package com.zweifreunde.org.client.debug;

/**
 * Created by meier on 07.02.15.
 */
public interface IDebug {
    public void info(String msg);
    public void debug(String msg);
    public void warning(String msg);
    public void error(String msg);
}
