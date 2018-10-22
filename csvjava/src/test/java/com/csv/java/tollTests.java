package com.csv.java;


import org.apache.commons.codec.binary.Base64;
import org.junit.Test;



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

}
