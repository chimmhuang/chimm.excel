package com.github.chimmhuang.excel.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Chimm Huang
 */
public class FileUtils {
    private FileUtils() { }

    public static byte[] readFileToByteArray(File file) throws IOException {
        byte[] bytes = null;
        try (FileInputStream fis = new FileInputStream(file)) {
            bytes = new byte[(int) file.length()];
            fis.read(bytes);
        } catch (IOException e) {
            throw e;
        }
        return bytes;
    }

}
