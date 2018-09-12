package com.csv.java.common.tool;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


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
}