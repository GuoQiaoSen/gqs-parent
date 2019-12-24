package com.gqs.util;

import java.io.*;

/**
 * 文件工具类
 *
 * @author 郭乔森
 * @create 2019-10-18 13:53
 */
public class FileUtil {

    public static void main(String[] args) throws IOException {
        File[] file = null;
        System.out.println(file);
    }

    /**
     * 输入流转file
     *
     * @param ins
     * @param file
     */
    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
