package com.ewiderbuy.produce.common.tool;

import java.util.UUID;

public class UUIDUtil {
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        return uuid;
    }
}
