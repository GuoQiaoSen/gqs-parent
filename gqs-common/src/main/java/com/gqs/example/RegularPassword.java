package com.gqs.example;

/**
 * 使用正则控制密码安全等级
 *
 * @author 郭乔森
 * @create 2020-06-12 16:45
 */
public class RegularPassword {
    public static void main(String[] args) {
        // 匹配 大写字母、小写字母、 数字、特殊符号
//        final String PW_PATTERN = "^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$";
        // 匹配 字母、数组、特殊符号
        final String PW_PATTERN = "^(?=.*[a-zA-Z])(?=.*[1-9])(?=.*[\\W]).{6,}$";


        String pw1 = "ABCDEFGHIG";
        String pw2 = "abcdefghig";
        String pw3 = "0123456789";
        String pw4 = "!@#$%^&*()";
        String pw5 = "ABCDEabcde";
        String pw6 = "ABCDE01234";
        String pw7 = "ABCDE!@#$%";
        String pw8 = "abcde01234";
        String pw9 = "abcde!@#$%";
        String pw10 = "01234!@#$%";
        String pw11 = "abcde01234!@#$%";
        String pw12 = "ABcde01234!@#$%";
        String pw13 = "ABCDEabcde!@#$%";
        String pw14 = "ABCDEabcde01234";
        String pw15 = "Aa0!";
        //符合要求密码
        String pw16 = "ABCabc012!@#";

        System.out.println(pw1.matches(PW_PATTERN));
        System.out.println(pw2.matches(PW_PATTERN));
        System.out.println(pw3.matches(PW_PATTERN));
        System.out.println(pw4.matches(PW_PATTERN));
        System.out.println(pw5.matches(PW_PATTERN));
        System.out.println(pw6.matches(PW_PATTERN));
        System.out.println(pw7.matches(PW_PATTERN));
        System.out.println(pw8.matches(PW_PATTERN));
        System.out.println(pw9.matches(PW_PATTERN));
        System.out.println(pw10.matches(PW_PATTERN));
        System.out.println(pw11.matches(PW_PATTERN));
        System.out.println(pw12.matches(PW_PATTERN));
        System.out.println(pw13.matches(PW_PATTERN));
        System.out.println(pw14.matches(PW_PATTERN));
        System.out.println(pw15.matches(PW_PATTERN));
        System.out.println(pw16.matches(PW_PATTERN));
    }
}
