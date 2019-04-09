package com.gqs.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 描述:
 * 时间工具类
 *
 * @author 郭乔森
 * @create 2019-04-09 18:52
 */
public class DateUtils {

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
}
