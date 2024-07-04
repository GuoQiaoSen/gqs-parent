package com.gqs.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 身份证号校验工具类
 *
 * @author guoqiaosen
 * @create 2024/07/04
 */
public class IdCardUtil {

    // 加权因子
    private static final int[] WEIGHTING_FACTOR = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    // 校验码
    private static final String[] VERIFICATION_CODE = {"1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2"};

    public static void main(String[] args) {
        String idCard = "130182199604285714";
        boolean b = checkIdCard(idCard);
        System.out.println(b);
    }

    public static boolean checkIdCard(String idCard) {
        // 校验：身份证号长度
        if (idCard == null || !idCard.matches("\\d{17}(\\d|x|X)$")) {
            return false;
        }
        Date d = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        int year = Integer.parseInt(df.format(d));
        // 7-10位是出生年份，范围应该在1900-当前年份之间
        if (Integer.parseInt(idCard.substring(6, 10)) < 1900 || Integer.parseInt(idCard.substring(6, 10)) > year) {
            return false;
        }
        // 11-12位代表出生月份，范围应该在01-12之间
        if (Integer.parseInt(idCard.substring(10, 12)) < 1 || Integer.parseInt(idCard.substring(10, 12)) > 12) {
            return false;
        }
        // 13-14位是出生日期，范围应该在01-31之间
        if (Integer.parseInt(idCard.substring(12, 14)) < 1 || Integer.parseInt(idCard.substring(12, 14)) > 31) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum += (idCard.charAt(i) - '0') * WEIGHTING_FACTOR[i];
        }
        int y = sum % 11;
        if (!VERIFICATION_CODE[y].equalsIgnoreCase(idCard.substring(17))) {// 第18位校验码错误
            return false;
        }
        return true;
    }
}
