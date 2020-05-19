package com.test.file;

import java.io.File;
import java.util.Date;

public class DeleteExpiredFile {

    private static final String ROOT_DIR_HISTORY_PATH = "/data";

    public void deleteExpiredFileTask() {
        File file = new File(ROOT_DIR_HISTORY_PATH);
        deleteExpiredFile(file);
    }

    private void deleteExpiredFile(File file) {
        if (!file.exists()) return;
        if (!file.isDirectory()) {
            determineExpiredFile(file);
        } else {
            for (File f : file.listFiles()) {
                deleteExpiredFile(f);
            }
        }
    }

    private void determineExpiredFile(File file) {
        long lastModifiedTime = file.lastModified();
        long currentTime = new Date().getTime();
        long timeInterval = 30 * 24 * 60 * 60 * 1000;
        if (currentTime - lastModifiedTime > timeInterval) {
            file.delete();
        }
    }

}
