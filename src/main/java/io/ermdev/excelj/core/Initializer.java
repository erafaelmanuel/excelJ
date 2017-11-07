package io.ermdev.excelj.core;

import io.ermdev.excelj.config.AppConfig;

public class Initializer {

    public static void showLogs() {
        AppConfig.SHOW_LOG = true;
    }

    public static void hideLogs() {
        AppConfig.SHOW_LOG = false;
    }
}
