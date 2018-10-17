package com.csv.java.common.tool;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class DateUtil {
    public static String date2String(Date d, String format){
        String ret = "";
        DateFormat format1 = new SimpleDateFormat(format);
        ret = format1.format(d);
        return ret;
    }
}
