
/**
 * @Title: Md5.java
 * @Package com.linkage.gtja.util
 * @Description: Md5加密
 * Copyright: Copyright (c) 2015
 *
 * @author Developer-liqiannan
 * @date 2015年12月18日 下午5:07:13
 * @version V1.0
 */
package com.ewiderbuy.produce.common.tool;

import java.security.MessageDigest;

/**
 * @ClassName: Md5
 * @Description: Md5加密
 * @author Developer-liqiannan
 *
 */
public class Md5Util {

    public final static String MD5(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4',
                '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            byte[] btInput = s.getBytes();
            //获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            //使用指定的字节更新摘要
            mdInst.update(btInput);
            //获得密文
            byte[] md = mdInst.digest();
            //把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
