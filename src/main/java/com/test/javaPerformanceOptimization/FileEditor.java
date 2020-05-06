package com.test.javaPerformanceOptimization;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FileEditor {
    private static class FileData {
        private Path filePath;
        private SoftReference<byte[]> dataRef;

        public FileData(Path filePath) {
            this.filePath = filePath;
            this.dataRef = new SoftReference<byte[]>(new byte[0]);
        }

        public Path getFilePath() {
            return filePath;
        }

        public byte[] getData() throws IOException {
            byte[] dataArray = dataRef.get();
            if (dataArray == null || dataArray.length == 0) {
                dataArray = readFile();
                dataRef  = new SoftReference<byte[]>(dataArray);
                dataArray = null;
            }
            return dataRef.get();
        }

        private byte[] readFile() throws IOException {
            return Files.readAllBytes(filePath);
        }
    }

    private FileData currentFileData;
    private Map<Path, FileData> openedFiles = new HashMap<>();

    public void switchTo(String filePath) {
        Path path = Paths.get(filePath).toAbsolutePath();
        if (openedFiles.containsKey(path)) {
            currentFileData = openedFiles.get(path);
        } else {
            currentFileData = new FileData(path);
            openedFiles.put(path, currentFileData);
        }
    }

    public void useFile() throws IOException {
        if (currentFileData != null) {
            System.out.println(String.format("", currentFileData.getFilePath(), currentFileData.getData().length));
        }
    }

    public static void main(String[] args) throws IOException {
        FileEditor fe = new FileEditor();
        for (int i = 0; i < 100; i++) {
            fe.switchTo("你的文件夹");
            fe.useFile();
        }
    }
}
