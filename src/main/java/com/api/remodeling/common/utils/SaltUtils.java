package com.api.remodeling.common.utils;

import java.util.Random;

/**
 * 随机盐生成工具类
 *
 * @author jinglv
 * @date 2021/01/31
 */
public class SaltUtils {
    /**
     * 生成Salt(随机字符)的静态方法
     *
     * @param n 字符长度
     * @return 返回随机盐字符串
     */
    public static String getSalt(int n) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = chars[new Random().nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
}
