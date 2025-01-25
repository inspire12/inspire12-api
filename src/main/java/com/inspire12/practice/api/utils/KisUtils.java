package com.inspire12.practice.api.utils;

import org.springframework.util.StringUtils;

public class KisUtils {

    public static String getOrderNo(String odno) {
        if (StringUtils.hasText(odno)) {
            Integer orderNoInteger = Integer.valueOf(odno);
            return String.valueOf(orderNoInteger);
        } else {
            return odno;
        }
    }

    public static Integer trimZero(String wsStr) {
        if (StringUtils.hasText(wsStr)) {
            return Integer.valueOf(wsStr);
        } else {
            return null;
        }
    }
}
