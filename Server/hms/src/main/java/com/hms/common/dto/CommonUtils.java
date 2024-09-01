package com.hms.common.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonUtils {

    public static boolean isNotNullOrEmpty(Object obj) {
        return obj != null && !obj.toString().isEmpty();
    }

    public static boolean isNotNullOrEmpty(Map<?, ?> map) {
        return map != null && !map.isEmpty();
    }

    public static boolean isNotNullOrEmpty(Boolean bool) {
        return Boolean.TRUE.equals(bool);
    }

    public static boolean isNotNullOrEmpty(StringBuilder sb) {
        return sb != null && sb.length() > 0;
    }

    public static boolean isNotZero(int val) {
        return val != 0;
    }

    public static boolean isNotNullOrEmpty(String value) {
        return value != null && !value.isEmpty() && !"null".equals(value);
    }

    public static boolean isNotNullOrEmpty(List<?> list) {
        return list != null && !list.isEmpty();
    }

    public static boolean isNotNullOrEmpty(HashMap<?, ?> hashMap) {
        return hashMap != null && !hashMap.isEmpty();
    }
}
