package com.ewiderbuy.produce.common.tool;

import com.ewiderbuy.produce.config.ConstantConfig;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.ArrayList;
import java.util.*;


/**
 * 公用工具类
 */
public class ServiceUtil {

    /**
     *
     * @Title: findListPage @Description: 分页工具类 根据start和limit过滤list @param
     * findListPage @return 定义传入参数 @return List
     * 返回类型 @throws
     */
    public Object findListPage(Object out, Integer limit, Integer start) {
        int index = 0;
        List<Object> hList = new ArrayList<Object>();
        List<Object> list = (List<Object>)out;
        if (limit != 0) {
            for (Object tt : list) {
                if (index >= (start -1)* limit  && index < limit * start) {
                    hList.add(tt);
                }
                index++;
            }
        }
        return hList;
    }

    /**
     * 获取Token
     * @param userId
     * @return
     */
    public static String getToken(String userId) {
        Date date = new Date();
        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("userId", userId);// 用户名
        payload.put("iat", date.getTime());// 生成时间
        Long tokenExt = ConstantConfig.CSCS_CONFIG_TOKENEXT !=null ?
                Long.parseLong(ConstantConfig.CSCS_CONFIG_TOKENEXT):180;
        payload.put("ext", date.getTime() + tokenExt * 1000 * 60 );// 过期时间2小时 2000 * 60 * 60
        String token = Jwt.createToken(payload);
        return token;
    }

    /**
     * Token 解析Token
     *
     * @param token
     * @return
     */
    public static String getEnCodeToken(String token) {
        String resToken = "";
        try {
            byte[] tt = new BASE64Decoder().decodeBuffer(token);
            resToken = new String(tt, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resToken;
    }
}