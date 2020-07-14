package com.gqs.example;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 郭乔森
 * @create 2020-05-08 15:45
 */
public class DateExample1 {
    public static void main(String[] args) {


        nextMonth();
    }

    /**
     * 下个月的时间
     */
    private static void nextMonth() {
        int y = 2020, m = 2;

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, y);
        cal.set(Calendar.MONTH, m - 1);
        cal.set(Calendar.DAY_OF_MONTH, 29);

        Date oneMonth = cal.getTime();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(oneMonth);

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(oneMonth);
        cal1.add(Calendar.MONTH, 1);

        System.out.println(format1);
        System.out.println(format.format(cal1.getTime()));
        System.out.println(cal1.get(Calendar.YEAR));
        System.out.println(cal1.get(Calendar.MONTH));
    }
}
