package com.csv.java.common.tool;


import org.apache.commons.lang3.StringUtils;
import org.phprpc.util.AssocArray;
import org.phprpc.util.Cast;
import org.phprpc.util.PHPSerializer;

import java.util.ArrayList;
import java.util.List;

public class PHPSerializeUtil {
    /**
     *  对php序列化的字符串，进行反序列化
     */
    public static List<String> unserializePHParray(String content){
        List<String> list = new ArrayList<String>();
        PHPSerializer p = new PHPSerializer();
        if (StringUtils.isEmpty(content))
            return list;
        try {
            AssocArray array = (AssocArray) p.unserialize(content.getBytes());
            for (int i = 0; i < array.size(); i++) {
                String t = (String) Cast.cast(array.get(i), String.class);
                list.add(t);
            }
        }catch (Exception e){
            System.out.println("反序列化PHParray: " + content + " 失败！！！" );
        }
        return list;
    }
}
