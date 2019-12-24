package com.gqs.example;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author 郭乔森
 * @create 2019-11-07 18:13
 */
public class FileExample {
    public static void main(String[] args) throws Exception {
        File file = new File("测试创建文件.txt");
        File file1 = new File("测试创建文件");
        file1.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
