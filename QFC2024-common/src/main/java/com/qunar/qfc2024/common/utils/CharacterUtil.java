package com.qunar.qfc2024.common.utils;

import java.util.regex.Pattern;

/**
 * 字符相关运算
 *
 * @author zhangge
 * @date 2024/6/21
 */
public class CharacterUtil {

    public static final String QUIT = "q";

    /**
     * 是否是汉字
     *
     * @param c 字符
     * @return 是否是汉字
     * @author zhangge
     * @date 2024/6/21
     */
    public static boolean isChinese(char c) {
        return c >= '\u4E00' && c <= '\u9FA5';
    }

    /**
     * 是否是英文字符
     *
     * @param c 字符
     * @return 是否是英文字符
     * @author zhangge
     * @date 2024/6/21
     */
    public static boolean isEnglish(char c) {
        return Character.isLetter(c);
    }

    /**
     * 是否是空白字符
     *
     * @param c 字符
     * @return 是否是空白字符
     * @author zhangge
     * @date 2024/6/21
     */
    public static boolean isBlank(char c) {
        return Character.isWhitespace(c);
    }

    /**
     * 定义一个正则表达式来匹配标点符号
     */
    private static final Pattern PUNCTUATION_PATTERN = Pattern.compile("[\\p{P}\\|\\p{Punct}]+");

    /**
     * 是否是标点符号
     *
     * @param c 字符
     * @return 是否是标点符号
     * @author zhangge
     * @date 2024/6/21
     */
    public static boolean isPunctuation(char c) {
        return PUNCTUATION_PATTERN.matcher(String.valueOf(c)).find();
    }

    /**
     * 是否是数字
     *
     * @param c 字符
     * @return 是否是数字
     * @author zhangge
     * @date 2024/6/21
     */
    public static boolean isDigit(char c) {
        return Character.isDigit(c);
    }
}
