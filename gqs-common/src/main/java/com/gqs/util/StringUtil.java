package com.gqs.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    /**
     * 字符串转集合
     *
     * @param str 例：a,b,c
     * @return List<String>
     */
    public static List<String> str2List(String str) {
        if (StringUtils.isBlank(str)) {
            return new ArrayList<>();
        }
        return Arrays.asList(str.split(","));
    }

}
