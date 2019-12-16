package com.gqs.example;

/**
 * @author 郭乔森
 * @create 2019-11-29 13:43
 */
public class 九九乘法表 {

    public static void main(String[] args) {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "×" + i + "=" + i * j + "\t");
            }
            System.out.println();
        }
    }
}
