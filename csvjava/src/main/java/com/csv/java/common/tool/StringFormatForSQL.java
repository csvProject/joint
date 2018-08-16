package com.csv.java.common.tool;

import com.csv.java.entity.CsvTemplateDetailDto;

import java.util.List;
import java.util.Map;

import static com.csv.java.config.ConstantConfig.FIELD_MATCHING_TABLE;


public class StringFormatForSQL{
    public static String ruleFormat(String key,String value, Map<String,String> arguments){
        String formatedStr = value;
        /*for (String key : arguments.keySet()) {
            formatedStr = formatedStr.replaceAll("\\$\\{:"+key+"\\}", arguments.get(key).toString());
        }*/
        for (String k : arguments.keySet()) {
            formatedStr = formatedStr.replaceAll("\\$\\{:"+k+"\\}","%`~"+ arguments.get(k).toString()+"^%`~");
        }
        String[] strings = formatedStr.split("%`~");

        String ret = "CONCAT(";
        for (int i =0;i<strings.length;i++){
            String s = strings[i];
            if(s.endsWith("^")){
                s = s.substring(0,s.length() - 1);
            }else{
                s = "'"+s+"'";
            }
            ret = ret +""+ s + ",";
        }
        if (ret.endsWith(",")) {
            ret = ret.substring(0,ret.length() - 1) + ") AS "+key;
        }else{
            ret = ret + ") AS "+key;
        }
        return ret;
    }

    public static String fieldListFormat(List<CsvTemplateDetailDto> list,Map<String,String> arguments){
        String ret = "SELECT \n  ";
        for(int i=0;i<list.size();i++){
            CsvTemplateDetailDto c = list.get(i);
            String k = c.getFieldKey();
            String v = c.getFieldValue();
            if(i != list.size()-1){
                ret = ret + StringFormatForSQL.ruleFormat(k,v, arguments) + ",\n  ";
            }else{
                ret = ret + StringFormatForSQL.ruleFormat(k,v, arguments) + "\n";
            }
        }
        ret = ret + "FROM \n  " + FIELD_MATCHING_TABLE;
        return ret;
    }
}

