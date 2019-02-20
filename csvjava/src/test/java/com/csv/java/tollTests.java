package com.csv.java;


import com.csv.java.common.tool.DateUtil;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static com.csv.java.config.ConstantConfig.ORDERDATA_INTERVAL_NDAY;


public class tollTests {

    @Test
    public void testbase64(){
        //String a = "opGUW2478";
        String a = "";
        String aa = new String(
                Base64.encodeBase64URLSafeString(a.getBytes()));
        System.out.println(aa);

    }

    @Test
    public void testr(){
        String ret = "avc";
        ret = ret.substring(0,1) + "123456789" +
                ret.substring(1);
        System.out.println(ret);
    }

    @Test
    public void testdt(){
        int ndate = 30;

        Calendar cal = Calendar.getInstance();
        Date edate = new Date();
        cal.setTime(edate);
        cal.add(Calendar.DATE,-1 * ndate);
        String endDate = DateUtil.date2String(edate,"yyyy-MM-dd");
        String startDate = DateUtil.date2String(cal.getTime(),"yyyy-MM-dd");
    }
    @Test
    public void getUUID(){
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();

        System.out.println(uuid);
    }
}
