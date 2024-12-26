package com.gqs.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * 计算百分比工具类
 *
 * @author guoqiaosen
 * @create 2024/09/10
 */
public class BigDecimalPercentageUtil {

    /**
     * 计算百分比
     * 计算方式：优先四舍五入，如果四舍五入后>=1,则舍弃多余小数
     *
     * @param part     分子
     * @param total    分母
     * @param newScale 保留小数位，如保留2位小数，则返回0.99=99%
     * @return 返回小数形式的百分比，如0.99 = 99%。返回的最大值是1，即100%
     */
    public static BigDecimal calculatePercentage(BigDecimal part, BigDecimal total, int newScale) {
        if (part == null || total == null || part.compareTo(BigDecimal.ZERO) == 0 || total.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        if (part.compareTo(total) >= 0) {
            return BigDecimal.ONE;
        }
        // 保留小数位的负指数
        BigDecimal newScaleMin = BigDecimal.TEN.pow(-newScale, MathContext.DECIMAL128);
        // 计算百分比。newScale+5是为了让结果尽可能精准
        BigDecimal percentage = part.divide(total, newScale + 5, RoundingMode.HALF_UP);

        // 检查结果并进行调整
        if (percentage.compareTo(newScaleMin) < 0) {
            // 如果计算结果值小于[保留小数位的负指数]，则直接返回[保留小数位的负指数]。避免分子分母差值过大导致舍入小数后返回0的情况
            return newScaleMin;
        }
        if (percentage.compareTo(BigDecimal.ONE) < 0) {
            // 如果小于1，保留三位小数并四舍五入
            return percentage.setScale(newScale, RoundingMode.HALF_UP);
        } else {
            // 如果大于等于1，舍弃多余小数
            return percentage.setScale(newScale, RoundingMode.DOWN);
        }
    }
}
