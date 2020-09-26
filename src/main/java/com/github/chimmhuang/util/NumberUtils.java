package com.github.chimmhuang.util;

import java.math.BigDecimal;

/**
 * a tool class about digital related operations
 *
 * @author Chimm Huang
 */
public class NumberUtils {
    private NumberUtils() {}

    public static BigDecimal nullToZero(BigDecimal arg) {
        return arg == null ? BigDecimal.ZERO : arg;
    }

    /**
     * sum function for BigDecimal
     */
    public static BigDecimal sum(BigDecimal... args) {
        BigDecimal result = BigDecimal.ZERO;
        for (BigDecimal arg : args) {
            if (arg != null) {
                result = result.add(arg);
            }
        }
        return result;
    }

    /**
     * multiply function for BigDecimal
     */
    public static BigDecimal multiply(BigDecimal... args) {
        BigDecimal result = BigDecimal.ONE;
        for (BigDecimal arg : args) {
            if (arg == null || arg.compareTo(BigDecimal.ZERO) == 0)
                return BigDecimal.ZERO;

            result = result.multiply(arg);
        }
        return result;
    }
}
