package com.gqs.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 描述:
 * decimal操作示例
 * decimal格式化
 *
 * @author 郭乔森
 * @create 2019-04-13 15:23
 */
public class DecimalExample {

    public static void main(String[] args) {
//        strToDecimal();
//        bigDecimalFormat();
        test();
    }

    public static void bigDecimalFormat() {
        //0 一个数字 ; # 一个数字，不包括 0
        //保留两位小数，如果不足两位小数则自动补零
        /*DecimalFormat df1 = new DecimalFormat("###0.00");
        df1.setRoundingMode(RoundingMode.HALF_UP);
        System.out.println(df1.format(124.3));
        System.out.println(df1.format(124.315));  //费解  没有四舍五入
        System.out.println(df1.format(124.325));  //费解  实现四舍五入

        //保留两位小数，不足两位则不补零
        DecimalFormat df2 = new DecimalFormat("###0.##");
        System.out.println(df2.format(124.6));
        System.out.println(df2.format(124));

        //保留两位小数，哪里不足位则补零
        DecimalFormat df3 = new DecimalFormat("000.00");
        System.out.println(df3.format(24));*/

        // 添加千位分隔符,保留2位小数，不足的补0,超出的四舍五入
        DecimalFormat df4 = new DecimalFormat("#,###.00");
        System.out.println(df4.format(new BigDecimal(3613)));
        System.out.println(df4.format(new BigDecimal(3613.61)));
        System.out.println(df4.format(new BigDecimal(34444444613.613)));
        System.out.println(df4.format(new BigDecimal(3613.6165)));
        System.out.println(df4.format(new BigDecimal(3613.6136)));
        // 传入null值会报错：java.lang.IllegalArgumentException: Cannot format given Object as a Number
//        System.out.println(df4.format(null));

    }

    public static void strToDecimal() {
        String a = "10.22";
        System.out.println(new BigDecimal(a));
    }

    public static void test(){
        BigDecimal bigDecimal = new BigDecimal("0.00");
        System.out.println(bigDecimal);
    }
}
