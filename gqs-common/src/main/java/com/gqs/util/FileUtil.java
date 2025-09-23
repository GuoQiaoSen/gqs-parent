package com.gqs.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * File转byte[]
     *
     * @param filePath
     * @return
     */
    public byte[] File2byte(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * byte[]转File
     *
     * @param bytes
     * @param filePath
     * @throws IOException
     */
    public void byte2File(byte[] bytes, String filePath) throws IOException {

        File file = new File(filePath);

        OutputStream output = new FileOutputStream(file);

        BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);

        bufferedOutput.write(bytes);
    }

    /**
     * 删除文件中的行
     */
    public static void deleteLines() {
        String filePath = "D:\\zxx_paper_answer_1206-0-1w.json";
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineCount = 0;
            while ((line = reader.readLine()) != null) {
                if (lineCount < 1000000) {
                    lines.add(line);
                } else {
                    break;
                }
                lineCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String remainingLine : lines) {
                writer.write(remainingLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
