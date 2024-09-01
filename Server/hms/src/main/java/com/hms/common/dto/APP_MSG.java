package com.hms.common.dto;

import java.util.HashMap;
import java.util.Map;


public class APP_MSG {

    public static final Map<String, String> MESSAGE = new HashMap<>();
    static {
        MESSAGE.put("COM01", "Created successfully");
        MESSAGE.put("COM02", "Updated successfully");
        MESSAGE.put("COM03", "Get successfully");
        MESSAGE.put("COM04", "Listed successfully");
        MESSAGE.put("COM05", "Deleted successfully");
    }

}
