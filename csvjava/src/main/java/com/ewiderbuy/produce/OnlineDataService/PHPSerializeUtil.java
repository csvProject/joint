package com.ewiderbuy.produce.OnlineDataService;


import de.ailis.pherialize.MixedArray;
import de.ailis.pherialize.Pherialize;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    /**
     *  对php序列化的字符串，进行反序列化，取得sizeid
     */
    public static int getSizeId(String content){
        int ret  = 99;

        if (content == null){
            return ret;
        }
        if ("".equals(content)){
            return ret;
        }

        Map<String,Integer> sizeMap = new  HashMap();
        MixedArray list;

        sizeMap.put("女性XS",0);
        sizeMap.put("女性S",1);
        sizeMap.put("女性M",2);
        sizeMap.put("女性L",3);
        sizeMap.put("女性XL",4);
        sizeMap.put("女性XXL",5);
        sizeMap.put("女性定制",6);
        sizeMap.put("男性XS",7);
        sizeMap.put("男性S",8);
        sizeMap.put("男性M",9);
        sizeMap.put("男性L",10);
        sizeMap.put("男性XL",11);
        sizeMap.put("男性XXL",12);
        sizeMap.put("男性定制",13);

        list = Pherialize.unserialize(content).toArray();
        if (list == null){
            return ret;
        }
        if (list.get("options") == null){
            return ret;
        }
        MixedArray options = list.getArray("options");
        String sexy ="";
        String size = "";
        for (int  i = 0,len =  options.values().size();i < len ;i++){
            MixedArray hash = options.getArray(i);
            if (hash.get("label").toString().indexOf("性别") >= 0 ||
                    hash.get("label").toString().indexOf("性別") >= 0){
                sexy =  hash.get("value").toString();
            }
            if (hash.get("label").toString().indexOf("サイズ") >= 0){
                size =  hash.get("value").toString();
            }
        }
        for(Map.Entry entry: sizeMap.entrySet())
        {
            if ((sexy + size).indexOf(entry.getKey().toString()) >= 0 ){
                return Integer.parseInt(entry.getValue().toString());
            }
        }
        return ret;
    }
}
