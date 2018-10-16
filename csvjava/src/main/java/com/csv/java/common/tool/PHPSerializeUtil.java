package com.csv.java.common.tool;


import de.ailis.pherialize.MixedArray;
import de.ailis.pherialize.Pherialize;

import java.util.LinkedHashMap;
import java.util.List;

public class PHPSerializeUtil {
    /**
     *  对php序列化的字符串，进行反序列化
     */
    public static String getProductOptions(String content){

        String ret  = "";
        MixedArray list;
        list = Pherialize.unserialize(content).toArray();
        if (list == null){
            return ret;
        }
        if (list.get("options") == null){
            return ret;
        }
        MixedArray options = list.getArray("options");
        for (int  i = 0,len =  options.values().size();i < len ;i++){
            MixedArray hash = options.getArray(i);
            if (i ==0 ){
                ret = hash.get("label").toString() + ":" + hash.get("value").toString();
            }else {
                ret = ret + "," + hash.get("label").toString() + ":" + hash.get("value").toString();
            }
        }
        return ret;
    }
}
