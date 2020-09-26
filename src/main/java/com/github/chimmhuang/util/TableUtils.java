package com.github.chimmhuang.util;

/**
 * @author Chimm Huang
 */
public class TableUtils {

    private TableUtils() {
    }

    private final static char[] COL_SET = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static char[] getColSet() {
        return COL_SET;
    }

    public static String getColName(Long index) {
        StringBuilder sb = new StringBuilder();
        int length = COL_SET.length;
        long idx = index;
        while (idx > length) {
            idx = idx / length;
            long b = idx % length;
            sb.append(COL_SET[(int) b - 1]);
        }
        sb.append(COL_SET[(int) idx - 1]);
        return sb.reverse().toString();
    }


    public static Integer getColIndex(String name) {
        String colSet = new String(COL_SET);
        char[] chars = name.toCharArray();
        int result = 0;
        for (int index = chars.length - 1; index >= 0; index--) {
            char ch = chars[index];
            int i = colSet.indexOf(ch);
            result += (i + 1) * Math.pow(chars.length, chars.length - index - 1);
        }
        return result;
    }
}
