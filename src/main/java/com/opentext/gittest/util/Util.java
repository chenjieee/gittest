package com.opentext.gittest.util;

import java.util.concurrent.TimeUnit;

public class Util {

    public static String fill(int id) {
        String index = String.valueOf(id);
        while (index.length() < 4) {
            index = '0' + index;
        }
        return index;
    }

    public static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            // ignore
        }
    }

}
