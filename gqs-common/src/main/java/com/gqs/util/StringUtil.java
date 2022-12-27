package com.gqs.util;

import java.util.regex.Pattern;

/**
 * @author guoqiaosen
 * @date 2022/6/27
 */
public class StringUtil {

    /**
     * 判断字符串是否全中文
     *
     * @param value
     * @return
     */
    public static boolean isChinese(String value) {
        Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
        return pattern.matcher(value).matches();
    }

    /**
     * 判断字符串是否全数字
     *
     * @param value
     * @return
     */
    public static boolean isNumber(String value) {
        return value.matches("[0-9]+");
    }

}
