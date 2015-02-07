package com.zweifreunde.org.client.debug;

/**
 * Created by meier on 07.02.15.
 */
public class ConsoleDebugImpl implements IDebug {
    @Override
    public void info(String msg) {
        System.out.println(msg);
    }

    @Override
    public void debug(String msg) {
        System.out.println(msg);
    }

    @Override
    public void warning(String msg) {
        System.out.println(msg);
    }

    @Override
    public void error(String msg) {
        System.out.println(msg);
    }
}
