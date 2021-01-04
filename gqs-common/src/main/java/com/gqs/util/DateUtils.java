package com.gqs.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 描述:
 * 时间工具类
 *
 * @author 郭乔森
 * @create 2019-04-09 18:52
 */
public class DateUtils {

    public static void main(String[] args) {

    }

    /**
     * 获取某月最后一天的日期
     *
     * @param year  2019
     * @param month 02
     * @return 2019-02-28
     */
    public static String lastDayOfMonth(String year, String month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.valueOf(year));
        calendar.set(Calendar.MONTH, Integer.valueOf(month));
        int day = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        String lastDayOfMonth = (new SimpleDateFormat("yyyy-MM-dd")).format(calendar.getTime());
        return lastDayOfMonth;
    }

    /**
     * 计算两个日期的天数差
     * （参数1和参数2不用区分大小，程序会自动判断，最后计算出两个时间的绝对天数差值）
     *
     * @param d1 时间1
     * @param d2 时间2
     * @return 绝对相差天数
     */
    public static int dayDiff(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();// 小时间
        Calendar c2 = Calendar.getInstance();// 大时间
        int compare = d1.compareTo(d2);
        if (compare < 0) {
            c1.setTime(d1);
            c2.setTime(d2);
        } else {
            c1.setTime(d2);
            c2.setTime(d1);
        }

        //获取日期在一年(月、星期)中的第多少天
        int day1 = c1.get(Calendar.DAY_OF_YEAR);
        int day2 = c2.get(Calendar.DAY_OF_YEAR);

        //获取当前日期所在的年份
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);

        //如果两个日期的是在同一年，则只需要计算两个日期在一年的天数差；
        //不在同一年，还要加上相差年数对应的天数，闰年有366天
        if (year1 != year2) //不同年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) //闰年
                {
                    timeDistance += 366;
                } else //不是闰年
                {
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else //同年
        {
            return day2 - day1;
        }
    }
}
